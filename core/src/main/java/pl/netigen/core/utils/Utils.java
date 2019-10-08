package pl.netigen.core.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import pl.netigen.core.R;

import static pl.netigen.core.config.Config.Companion;

public class Utils {

    public static void openMarketLink(Activity activity, String packageName) {
        openLink(activity, getMarketLink(activity, packageName));
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
    private static String getMarketLink(Activity activity, String packageName) {
        int link_id = Companion.isSamsung() ? R.string.samsung_app_id_link_netigen : R.string.play_app_id_link_netigen;
        return activity.getString(link_id) + packageName;
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

    public static void showLinkifyDialog(final Activity activity, int titleId, int stringID) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_CustomAlertDialog);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.linkify_dialog, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        ((TextView) dialogView.findViewById(R.id.title)).setText(activity.getString(titleId));
        ((TextView) dialogView.findViewById(R.id.infoText)).setText(activity.getString(stringID));
        dialogView.findViewById(R.id.button_ok).setOnClickListener(v -> dialog.dismiss());
        dialog.show();
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

    private static void showRateUsDialog(final Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_CustomAlertDialog);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_rate_us, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialogView.findViewById(R.id.button_rate_us_stars).setOnClickListener(v -> {
            openMarketLink(activity, activity.getPackageName());
            dialog.dismiss();
        });
        dialogView.findViewById(R.id.button_no).setOnClickListener(v -> dialog.dismiss());
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

    public static void showDialog(Activity activity, @Nullable final View.OnClickListener okClick, @Nullable final View.OnClickListener cancelClick, String title, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_CustomAlertDialog);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        ((TextView) dialogView.findViewById(R.id.title)).setText(title);
        ((TextView) dialogView.findViewById(R.id.infoText)).setText(msg);
        dialogView.findViewById(R.id.button_ok).setOnClickListener(v -> {
            dialog.dismiss();
            if (okClick != null) {
                okClick.onClick(v);
            }
        });
        dialogView.findViewById(R.id.button_cancel).setOnClickListener(v -> {
            dialog.dismiss();
            if (cancelClick != null) {
                cancelClick.onClick(v);
            }
        });
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