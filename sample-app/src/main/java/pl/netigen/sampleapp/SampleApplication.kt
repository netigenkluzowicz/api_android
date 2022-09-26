package pl.netigen.sampleapp

import android.app.Application
import timber.log.Timber

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "$TAG.${element.className.substringAfterLast(".")}.${element.methodName}"
                }
            },)
        }
    }

    companion object {
        const val TAG = "netigenApi"
    }
}
