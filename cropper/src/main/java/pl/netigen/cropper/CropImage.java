package pl.netigen.cropper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class CropImage {

    CropParams cropParams;
    CropFragment.OnCropFragmentInteractionListener listener;

    private CropImage(CropParams cropParams) {
        this.cropParams = cropParams;
    }

    private static final String TAG = "CropImage";
    private void tryShowCropFragment(CropParams cropParams) {
        this.listener = cropParams.listener;
        if (ActivityCompat.checkSelfPermission(cropParams.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openCropFragment();
        }else{
            Log.d(TAG, "tryShowCropFragment: ");
            listener.onPermissionNotGranted();
        }
    }

    private void openCropFragment() {
        CropFragment cropFragment = CropFragment.newInstance(this);
        cropFragment.show(cropParams.fragmentManager, "");
    }

    public static class Builder {

        private CropParams cropParams;

        public Builder(AppCompatActivity activity, CropFragment.OnCropFragmentInteractionListener listener, int frameId) {
            cropParams = new CropParams();
            cropParams.listener = listener;
            cropParams.fragmentManager = activity.getSupportFragmentManager();
            cropParams.frameId = frameId;
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

        public CropImage create() {
            return new CropImage(cropParams);
        }

        public void tryShowCropFragment() {
            create().tryShowCropFragment(cropParams);
        }

    }

}
