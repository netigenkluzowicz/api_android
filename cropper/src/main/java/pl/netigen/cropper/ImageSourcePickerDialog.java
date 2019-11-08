package pl.netigen.cropper;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import pl.netigen.core.netigenapi.NetigenDialogFragment;

public class ImageSourcePickerDialog extends NetigenDialogFragment {

    private ImageView backgroundLoginPopup;
    private ImageView galleryButton;
    private ImageView photoButton;
    private TextView titlePickerDialog;
    private OpenGalleryOrCamera listener;
    private boolean click = false;
    private CropParams cropParams;

    public ImageSourcePickerDialog() {

    }

    public static ImageSourcePickerDialog newInstance(@NonNull CropParams cropParams) {
        ImageSourcePickerDialog fragment = new ImageSourcePickerDialog();
        fragment.cropParams = cropParams;
        return fragment;
    }

    void openDialog(ImageSourcePickerDialog imageSourcePickerDialog, FragmentManager fragmentManager) {
        imageSourcePickerDialog.show(fragmentManager, getClass().getSimpleName());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.galery_or_camera_dialog, container, false);
        if (cropParams == null) {
            if (getCanCommitFragments()) {
                dismiss();
            }
            return view;
        }
        initViews(view);
        setTitleTextView();
        setClickListeners(view);
        setImages();
        return view;
    }

    private void initViews(View view) {
        backgroundLoginPopup = view.findViewById(R.id.backgroundLoginPopup);
        galleryButton = view.findViewById(R.id.galleryButton);
        photoButton = view.findViewById(R.id.photoButton);
        titlePickerDialog = view.findViewById(R.id.titleSourcePickerDialog);
    }

    private void setTitleTextView() {
        if (cropParams.textColor != null) {
            titlePickerDialog.setTextColor(cropParams.textColor);
        }
    }

    private void setClickListeners(View view) {
        view.findViewById(R.id.photoButton).setOnClickListener(v -> {
            if (listener != null)
                listener.openCamera();
            click = true;
            ImageSourcePickerDialog.this.dismiss();
        });
        view.findViewById(R.id.galleryButton).setOnClickListener(v -> {
            if (listener != null)
                listener.openGallery();
            click = true;
            ImageSourcePickerDialog.this.dismiss();
        });
    }

    private void setImages() {
        if (cropParams != null) {
            Glide.with(this).load(cropParams.bgPopupAskCameraOrPhoto).into(backgroundLoginPopup);
            Glide.with(this).load(cropParams.btnGallery).into(galleryButton);
            Glide.with(this).load(cropParams.btnPhoto).into(photoButton);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog == null || dialog.getWindow() == null)
            return;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onDismiss(@NotNull DialogInterface dialog) {
        if (!click) {
            if (listener != null)
                listener.onDismiss();
        }
        super.onDismiss(dialog);
    }

    void setListener(OpenGalleryOrCamera listener) {
        this.listener = listener;
    }
}
