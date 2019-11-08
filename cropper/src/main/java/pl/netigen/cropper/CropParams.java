package pl.netigen.cropper;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

class CropParams {
    @NonNull
    FragmentManager fragmentManager;
    @NonNull
    AppCompatActivity activity;
    @NonNull
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
