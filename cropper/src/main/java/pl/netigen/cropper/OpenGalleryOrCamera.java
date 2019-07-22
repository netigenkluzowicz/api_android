package pl.netigen.cropper;

public interface OpenGalleryOrCamera {

    void openGallery();

    void openCamera();

    void closedFragment();

    void onDismiss(boolean shouldDismissCropper);

}
