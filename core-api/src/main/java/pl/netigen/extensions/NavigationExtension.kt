package pl.netigen.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import timber.log.Timber

fun NavController.safeNavigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) {
    try {
        this.navigate(resId, args, navOptions)
    } catch (e: Exception) {
        Timber.d("safeNavigate: Double navigation on id:${resId}")
    }
}

fun Fragment.safeNavigate(@IdRes resId: Int, args: Bundle? = null, navOptions: NavOptions? = null) =
    findNavController().safeNavigate(resId, args, navOptions)

fun NavController.safeNavigate(navDirections: NavDirections) {
    try {
        this.navigate(navDirections)
    } catch (e: Exception) {
        Timber.d("safeNavigate: Double navigation on $navDirections")
    }
}

fun Fragment.safeNavigate(navDirections: NavDirections) = findNavController().safeNavigate(navDirections)