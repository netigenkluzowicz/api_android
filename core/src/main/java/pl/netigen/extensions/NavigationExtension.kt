package androidx.navigation

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun NavController.safeNavigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
    try {
        this.navigate(resId, args, navOptions)
    } catch (e: Exception) {
        Log.e("NavController", "safeNavigate: Double navigation on id:${NavDestination.getDisplayName(context, resId)}")
    }
}

fun Fragment.safeNavigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) =
    findNavController().safeNavigate(resId, args, navOptions)