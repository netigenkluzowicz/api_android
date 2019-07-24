package pl.netigen.cropper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CropFragment extends AppCompatDialogFragment implements OpenGalleryOrCamera {

    private static final int CAMERA_PIC_REQUEST = 1235;
    private static final int SELECT_IMAGE = 54612;

    private CropImageView cropImageView;
    private ImageView roundImageButton;
    private ImageView cropImageButton;

    private CropParams cropParams;
    private OnCropFragmentInteractionListener listener;
    private Uri value;

    public CropFragment() {

    }

    public static CropFragment newInstance(CropperManager cropperManager) {
        CropFragment fragment = new CropFragment();
        fragment.cropParams = cropperManager.cropParams;
        fragment.setListener(cropperManager.cropParams.listener);
        return fragment;
    }

    private void setListener(OnCropFragmentInteractionListener listener) {
        this.listener = listener;
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

    @Override
    public void onStart() {
        super.onStart();
        setDialogMatchParent();
    }

    void setDialogMatchParent() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crop, container, false);
        initViews(view);
        openDialog();
        return view;
    }

    private void openDialog() {
        ImageSourcePickerDialog imageSourcePickerDialog = ImageSourcePickerDialog.newInstance(cropParams);
        imageSourcePickerDialog.setListener(this);
        FragmentActivity activity = getActivity();

        if (activity != null) {
            FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
            imageSourcePickerDialog.openDialog(imageSourcePickerDialog, supportFragmentManager);
        }
    }

    private void initViews(View view) {
        cropImageButton = view.findViewById(R.id.saveImageView);
        this.cropImageView = view.findViewById(R.id.cropImageView);
        roundImageButton = view.findViewById(R.id.rotateImageView);
        cropImageButton.setOnClickListener(onSaveClicked());
        roundImageButton.setOnClickListener(v -> {
            cropImageView.rotateImage(-90);
        });
        cropImageView.setOnCropImageCompleteListener((view1, result) -> {
            if (listener != null) {
                listener.saveCroppedImage(result.getUri());
            }
            dismiss();
        });
    }

    private File getFileForSaving() {
        if (getActivity() == null) return null;
        File mediaStorageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (mediaStorageDir == null) return null;
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmssSS", Locale.US).format(new Date());
        String imageName = timeStamp + ".jpg";
        return new File(mediaStorageDir.getPath() + File.separator + imageName);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private View.OnClickListener onSaveClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = CropFragment.this.getActivity();
                if (activity != null) {
                    cropImageView.saveCroppedImageAsync(Uri.fromFile(CropFragment.this.getFileForSaving()));
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            closeFragment();
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

    private Uri getUri() {
        if (getActivity() == null) {
            return null;
        }
        File path = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(path, System.currentTimeMillis() + ".jpeg");
        FileOutputStream fileOutPutStream;
        try {
            fileOutPutStream = new FileOutputStream(imageFile);
            fileOutPutStream.flush();
            fileOutPutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Context context = getActivity();

        if (context == null)
            return null;

        return FileProvider.getUriForFile(context,
                context.getPackageName() + ".provider",
                imageFile);
    }

    @Override
    public void closeFragment() {
        FragmentActivity activity = getActivity();
        if (activity != null)
            dismiss();
    }

    @Override
    public void onDismiss() {
        dismiss();
    }

    public interface OnCropFragmentInteractionListener {
        void saveCroppedImage(Uri uri);

        void onPermissionNotGranted();
    }
}
