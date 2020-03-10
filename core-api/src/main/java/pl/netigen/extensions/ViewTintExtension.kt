package pl.netigen.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun ImageView.setBackgroundTint(@ColorRes colorRes: Int, porterDuff: PorterDuff.Mode) {
    this.background.setColorFilter(ContextCompat.getColor(this.context, colorRes), porterDuff)
}


fun ImageView.setTint(@ColorRes colorRes: Int, porterDuff: PorterDuff.Mode) {
    this.setColorFilter(ContextCompat.getColor(this.context, colorRes), porterDuff)
}

fun Drawable.setTint(context: Context, @ColorRes colorRes: Int, porterDuff: PorterDuff.Mode) {
    this.setColorFilter(ContextCompat.getColor(context, colorRes), porterDuff)
}