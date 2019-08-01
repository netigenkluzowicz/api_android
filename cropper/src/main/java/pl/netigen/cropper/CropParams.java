package pl.netigen.cropper;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

class CropParams {

    FragmentManager fragmentManager;
    AppCompatActivity activity;
    CropFragment.OnCropFragmentInteractionListener listener;
    Integer textColor;
    @DrawableRes
    int bgPopupAskCameraOrPhoto;
    @DrawableRes
    int btnGallery;
    @DrawableRes
    int btnPhoto;

    CropParams() {
    }

}
