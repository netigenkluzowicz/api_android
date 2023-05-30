object Versions {
    const val legacySupportV13 = "1.0.0"
    const val playServicesAds = "22.1.0"
    const val constraintLayout = "2.1.4"
    const val material = "1.6.1"
    const val gson = "2.9.1"
    const val glide = "4.13.2"
    const val appcompat = "1.5.1"
    const val userMessagingPlatform = "2.0.0"
    const val coreKTX = "1.8.0"
    const val kotlin = "1.5.31"
    const val okhttp = "4.10.0"
    const val retrofit = "2.9.0"
    const val junit = "4.12"
    const val testRunner = "1.3.0"
    const val espressoCore = "3.2.0"
    const val billing = "5.0.0"
    const val navigation = "2.5.2"
    const val coroutines = "1.6.4"
    const val room = "2.4.3"
    const val timber = "5.0.1"
    const val playCore = "1.10.2"
}

object Libraries {
    val legacySupportV13 = "androidx.legacy:legacy-support-v13:${Versions.legacySupportV13}"

    val material = "com.google.android.material:material:${Versions.material}"

    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val playServicesAds = "com.google.android.gms:play-services-ads:${Versions.playServicesAds}"

    val userMessagingPlatform = "com.google.android.ump:user-messaging-platform:${Versions.userMessagingPlatform}"

    val viewModelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
    val liveDataKTX = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"
    val runtimeKTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"

    val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"

    val navigationFragmentKTX = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUiKTX = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"
    val kotlinStdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    val junit = "junit:junit:${Versions.junit}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    val billing = "com.android.billingclient:billing-ktx:${Versions.billing}"

    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomKTX = "androidx.room:room-ktx:${Versions.room}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val playCore = "com.google.android.play:core:${Versions.playCore}"
}
