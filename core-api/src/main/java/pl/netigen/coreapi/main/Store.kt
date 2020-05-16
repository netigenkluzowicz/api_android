package pl.netigen.coreapi.main

/**
 * Possible target Stores for Application release
 * - [Google Play](https://play.google.com/store)
 * - [Samsung Galaxy Store](https://www.samsung.com/apps/galaxy-store/)
 * - [Huawei AppGallery](https://huaweimobileservices.com/appgallery/)
 */
enum class Store {
    /**
     * For [Google Play](https://play.google.com/store) application target store.
     *
     */
    GOOGLE_PLAY,

    /**
     * For [Samsung Galaxy Store](https://www.samsung.com/apps/galaxy-store/) application target store.
     *
     */
    SAMSUNG,

    /**
     * For [Huawei AppGallery](https://huaweimobileservices.com/appgallery/) application target store.
     *
     */
    HUAWEI
}