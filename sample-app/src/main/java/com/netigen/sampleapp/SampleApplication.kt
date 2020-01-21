package com.netigen.sampleapp

import android.app.Application
import timber.log.Timber

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement) =
                "netigenApi.${element.className.substringAfterLast(".")}.${element.methodName}"
        })
    }
}