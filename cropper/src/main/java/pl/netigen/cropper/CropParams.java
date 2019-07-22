package pl.netigen.cropper;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class CropParams {

    FragmentManager fragmentManager;
    AppCompatActivity activity;
    @DrawableRes
    int bgPopupAskCameraOrPhoto;
    @DrawableRes
    int btnGallery;
    @DrawableRes
    int btnPhoto;
    int frameId;
    int textColor;
    CropFragment.OnCropFragmentInteractionListener listener;

    CropParams() {
    }

    CropParams(int bgPopupAskCameraOrPhoto, int btnGallery, int btnPhoto) {
        this.bgPopupAskCameraOrPhoto = bgPopupAskCameraOrPhoto;
        this.btnGallery = btnGallery;
        this.btnPhoto = btnPhoto;
    }
}
