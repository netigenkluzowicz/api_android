package pl.netigen.cropper;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class ImageSourcePickerDialog extends AppCompatDialogFragment {

    private static final String TAG = "ImageSourcePickerDialog";
    private ImageView backgroundLoginPopup;
    private ImageView galleryButton;
    private ImageView photoButton;
    private OpenGalleryOrCamera listener;
    private boolean click = false;
    private CropParams cropParams;

    public ImageSourcePickerDialog() {
        Log.d(TAG, "ImageSourcePickerDialog: ");
    }

    public static ImageSourcePickerDialog newInstance(CropParams cropParams) {
        ImageSourcePickerDialog fragment = new ImageSourcePickerDialog();
        fragment.cropParams = cropParams;
        return fragment;
    }

    void openDialog(ImageSourcePickerDialog imageSourcePickerDialog, FragmentManager fragmentManager) {
        imageSourcePickerDialog.show(fragmentManager, "tag");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.galery_or_camera_dialog, container, false);
        backgroundLoginPopup = view.findViewById(R.id.backgroundLoginPopup);
        galleryButton = view.findViewById(R.id.galleryButton);
        photoButton = view.findViewById(R.id.photoButton);
        initClickListener(view);
        addImageToView();
        return view;
    }

    private void addImageToView() {
        if (cropParams != null) {
            Glide.with(this).load(cropParams.bgPopupAskCameraOrPhoto).into(backgroundLoginPopup);
            Glide.with(this).load(cropParams.btnGallery).into(galleryButton);
            Glide.with(this).load(cropParams.btnPhoto).into(photoButton);
        }
    }

    private void initClickListener(View view) {
        view.findViewById(R.id.photoButton).setOnClickListener(v ->  {
            if (listener != null)
                listener.openCamera();
            click = true;
            dismiss();
        });
        view.findViewById(R.id.galleryButton).setOnClickListener(v -> {
            if (listener != null)
                listener.openGallery();
            click = true;
            dismiss();
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
//        setStyle(DialogFragment.STYLE_NO_FRAME);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!click) {
            if (listener != null)
                listener.onDismiss(true);
        }
        listener.onDismiss(false);
        super.onDismiss(dialog);
    }

    void setListener(OpenGalleryOrCamera listener) {
        this.listener = listener;
    }
}
