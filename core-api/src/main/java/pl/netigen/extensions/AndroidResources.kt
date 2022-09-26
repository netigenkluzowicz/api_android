package pl.netigen.extensions

import android.content.res.Resources

/**
 * Converts this pixels value to dp (density-independent pixels) for current device screen, rounded down as in [Float.toInt]
 *
 * @return Dp value converted from px value
 */
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

/**
 * Converts this dp value (density-independent pixels) to pixels value for current device screen, rounded down as in [Float.toInt]
 *
 * @return Px value converted from dp value
 */
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
