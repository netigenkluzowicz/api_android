package pl.netigen.extensions

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

fun Drawable.startAnim() = (this as Animatable).start()

fun Drawable.stopAnim() = (this as Animatable).stop()

fun Drawable.loopAnim(animatedView: View) {
    startAnim()
    AnimatedVectorDrawableCompat.registerAnimationCallback(
        this,
        object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                animatedView.postOnAnimation {
                    this@loopAnim.startAnim()
                }
            }
        },
    )
}
