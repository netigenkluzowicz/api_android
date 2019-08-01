package pl.netigen.cropper;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CropperManager {

    CropParams cropParams;
    private CropFragment.OnCropFragmentInteractionListener listener;

    private CropperManager(CropParams cropParams) {
        this.cropParams = cropParams;
    }

    private void tryShowCropFragment(CropParams cropParams) {
        this.listener = cropParams.listener;
        if (ActivityCompat.checkSelfPermission(cropParams.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openCropFragment();
        } else {
            listener.onPermissionNotGranted();
        }
    }

    private void openCropFragment() {
        CropFragment cropFragment = CropFragment.newInstance(this);
        cropFragment.show(cropParams.fragmentManager, CropFragment.class.getSimpleName());
    }

    public static class Builder {

        private CropParams cropParams;

        public Builder(AppCompatActivity activity, CropFragment.OnCropFragmentInteractionListener listener) {
            cropParams = new CropParams();
            cropParams.listener = listener;
            cropParams.fragmentManager = activity.getSupportFragmentManager();
            cropParams.activity = activity;
        }

        public Builder setBgPopupAskCameraOrPhoto(int bgPopupAskCameraOrPhoto) {
            this.cropParams.bgPopupAskCameraOrPhoto = bgPopupAskCameraOrPhoto;
            return this;
        }

        public Builder setBtnGallery(int btnGallery) {
            this.cropParams.btnGallery = btnGallery;
            return this;
        }

        public Builder setBtnPhoto(int btnPhoto) {
            this.cropParams.btnPhoto = btnPhoto;
            return this;
        }

        public Builder setTextColor(int textColor) {
            this.cropParams.textColor = textColor;
            return this;
        }

        public CropperManager create() {
            return new CropperManager(cropParams);
        }

        public void tryShowCropFragment() {
            create().tryShowCropFragment(cropParams);
        }
    }
}
