import org.gradle.api.JavaVersion

object LibraryPlugins {
    val library = "com.android.library"
    val kotlinAndroid = "kotlin-android"
    val kotlinKapt = "kotlin-kapt"
    val androidExtensions = "kotlin-android-extensions"
    val githubDcendents = "com.github.dcendents.android-maven"
}

object BuildPlugins{

    val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Config {
    val minSdk = 16
    val compileSdk = 28
    val targetSdk = 28
    val buildTools = "28.0.3"
    val group = "com.github.netigenkluzowicz"
}

object Versions {
    val legacySupportV4 = "1.0.0"
    val legacySupportV13 = "1.0.0"
    val recyclerview = "1.0.0"
    val playServicesAds = "18.1.1"
    val constraintLayout = "1.1.3"
    val material = "1.1.0-alpha04"
    val gson = "2.8.5"
    val glide = "4.9.0"
    val appcompat = "1.1.0"
    val consentLibrary = "1.0.7"
    val coreKTX = "1.1.0"
    val archVersion = "2.2.0-alpha04"
    val okhttp = "3.12.0"
    val okio = "2.2.2"
    val retrofit = "2.6.1"
    val kotlin = "1.3.50"
    val androidMavenGradlePlugin = "2.1"
    val gradle = "3.5.0"
    val buildToolsVersion = "28.0.3"
}

object Dependencies {

    val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val playServicesAds = "com.google.android.gms:play-services-ads:${Versions.playServicesAds}"
    val legacySupportV13 = "androidx.legacy:legacy-support-v13:${Versions.legacySupportV13}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val material = "com.google.android.material:material:${Versions.material}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val consentLibrary = "com.google.android.ads.consent:consent-library:${Versions.consentLibrary}"
    val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"
    val kotlinStdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.archVersion}"
    val viewmodelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archVersion}"
    val livedataKTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.archVersion}"
    val runtimeKTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.archVersion}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okio = "com.squareup.okio:okio:${Versions.okio}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    val buildGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    val dcendantsMavenGradle = "com.github.dcendents:android-maven-gradle-plugin:${Versions.androidMavenGradlePlugin}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinAndroidExtensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
}

