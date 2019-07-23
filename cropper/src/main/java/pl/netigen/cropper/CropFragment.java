package pl.netigen.cropper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CropFragment extends AppCompatDialogFragment implements OpenGalleryOrCamera {

    private static final int CAMERA_PIC_REQUEST = 1235;
    private static final int SELECT_IMAGE = 54612;
    private CropParams cropParams;
    private CropImageView cropImageView;
    private ImageView roundImageButton;
    private ImageView cropImageButton;
    private static final String TAG = "CropFragment";

    private OnCropFragmentInteractionListener listener;
    private Uri value;

    private CropFragment() {
        Log.d(TAG, "CropFragment: ");
    }

    public static CropFragment newInstance(CropImage cropImage) {
        Log.d(TAG, "newInstance: 1");
        CropFragment fragment = new CropFragment();
        fragment.cropParams = cropImage.cropParams;
        fragment.setListener(cropImage.cropParams.listener);
        Log.d(TAG, "newInstance: fragment.listener null " + (null==fragment.listener));
        return fragment;
    }

    private void setListener(OnCropFragmentInteractionListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogSize(1.0, 1.0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crop, container, false);
        Log.d(TAG, "onCreateView: ");
        init(view);
        openDialog();
        return view;
    }

    private void init(View view) {
        Log.d(TAG, "init: listener null " + (listener == null));
        cropImageButton = view.findViewById(R.id.cropImageButton);
        this.cropImageView = view.findViewById(R.id.cropImageView);
        roundImageButton = view.findViewById(R.id.roundImageButton);
        cropImageButton.setOnClickListener(onSaveClicked());
        roundImageButton.setOnClickListener(v -> {
            cropImageView.rotateImage(-90);
        });
        cropImageView.setOnCropImageCompleteListener((view1, result) -> {
            if (listener != null){
                listener.saveCroppedImage(result.getUri());
            }
            dismiss();
        });
    }

    private void openDialog() {
        ImageSourcePickerDialog imageSourcePickerDialog = ImageSourcePickerDialog.newInstance(cropParams);
        imageSourcePickerDialog.setListener(this);
        FragmentActivity activity = getActivity();
        Log.d(TAG, "openDialog: activity null " + (activity==null));

        if (activity != null) {
            FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
            imageSourcePickerDialog.openDialog(imageSourcePickerDialog, supportFragmentManager);
        }
    }

    private Uri getUri() {
        if(getActivity()==null){
            return null;
        }
        File path = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(path, System.currentTimeMillis() + ".jpeg");
        FileOutputStream fileOutPutStream = null;
        try {
            fileOutPutStream = new FileOutputStream(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (fileOutPutStream != null) {
                fileOutPutStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (fileOutPutStream != null) {
                fileOutPutStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Context context = getContext();
        if (context == null)
            return null;

        return FileProvider.getUriForFile(context,
                BuildConfig.APPLICATION_ID + ".provider",
                imageFile);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private View.OnClickListener onSaveClicked() {
        return v -> {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                String packageName = activity.getPackageName();
                cropImageView.saveCroppedImageAsync(Uri.fromFile(getUri(packageName)));
            }
        };
    }

    private File getUri(String packagename) {
        File sourcePath = Environment.getExternalStorageDirectory();
        File mediaStorageDir = new File(sourcePath
                + "/Android/data/"
                + packagename
                + "/Pictures");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmssSS", Locale.US).format(new Date());
        File mediaFile;
        String mImageName = "Draw_" + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            closedFragment();
        } else {
            cropImageButton.setVisibility(View.VISIBLE);
            roundImageButton.setVisibility(View.VISIBLE);
            if (requestCode == CAMERA_PIC_REQUEST) {
                cropImageView.setImageUriAsync(value);
            }
            if (requestCode == SELECT_IMAGE) {
                if (data != null) {
                    Uri selectedImageUri = data.getData();
                    cropImageView.setImageUriAsync(selectedImageUri);
                }
            }
        }
    }

    @Override
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
    }

    @Override
    public void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        value = getUri();
        if (value != null) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, value);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        }
    }

    @Override
    public void closedFragment() {
        FragmentActivity activity = getActivity();
        if (activity != null)
            activity.onBackPressed();
    }

    @Override
    public void onDismiss(boolean shouldDismissCropper) {
        if(shouldDismissCropper){
            dismiss();
        }
    }

    public interface OnCropFragmentInteractionListener {
        void saveCroppedImage(Uri uri);
        void onPermissionNotGranted();
    }

    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        show(transaction, tag);
    }

    void setDialogSize(double heightMultiplier, double widthMultiplier) {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display;
        if (window != null) {
            display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.setGravity(Gravity.CENTER);
        }
    }
}
