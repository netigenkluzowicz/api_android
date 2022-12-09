package pl.netigen.core.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import pl.netigen.core.R;
import pl.netigen.core.config.AppConfig;


public class Utils {

    public static void openMarketLink(Activity activity, String packageName) {
        openLink(activity, getMarketLink(packageName));
    }

    public static boolean checkAndAskAndroidPermission(AppCompatActivity activity, String permissionString) {
        if (!isPermissionGranted(activity, permissionString)) {
            String[] permissionsArray = {permissionString};
            ActivityCompat.requestPermissions(activity, permissionsArray, 0);
            return false;
        }
        return true;
    }

    public static boolean isPermissionGranted(AppCompatActivity activity, String permissionString) {
        return ActivityCompat.checkSelfPermission(activity, permissionString) == PackageManager.PERMISSION_GRANTED;
    }

    @NonNull
    private static String getMarketLink(String packageName) {
        return AppConfig.Companion.getMarketLinkPrefix() + packageName;
    }

    public static void showShortToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static void openLink(Activity activity, String string) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(string));
            activity.startActivity(intent);
        } catch (Exception e) {
            showShortToast(activity, activity.getResources().getString(R.string.msg_browser_not_found_netigen));
        }
    }

    public static void showInfoDialog(Activity activity, int titleId, int stringID) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_CustomAlertDialog);
        builder.setTitle(titleId);
        builder.setMessage(activity.getString(stringID))
                .setPositiveButton(R.string.ok_netigen,
                        (d, id) -> d.dismiss());
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showDialog(Activity activity,
                                  int titleId,
                                  int stringID,
                                  String positiveText,
                                  DialogInterface.OnClickListener positiveOnClickListener,
                                  String negativeText,
                                  DialogInterface.OnClickListener noOnClickListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_CustomAlertDialog);
        builder.setTitle(titleId);
        builder.setMessage(activity.getString(stringID));
        if (positiveOnClickListener != null) {
            builder.setPositiveButton(positiveText, positiveOnClickListener);
        }
        if (noOnClickListener != null) {
            builder.setNegativeButton(negativeText, noOnClickListener);
        }
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showLongToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public static void showSnackBarLong(Activity activity, String string) {
        Snackbar.make(activity.findViewById(android.R.id.content), string, Snackbar.LENGTH_LONG)
                .show();
    }

    public static void checkAndAskForMultiplePermissions(AppCompatActivity appCompatActivity, String[] permissions) {
        ActivityCompat.requestPermissions(appCompatActivity, permissions, 0);
    }
}
