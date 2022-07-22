package pl.netigen.core.main

import android.app.Application
import androidx.lifecycle.ViewModel
import pl.netigen.core.network.NetworkStatus
import pl.netigen.core.splash.CoreSplashVMImpl
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.gdpr.IGDPRConsent
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.main.ICoreViewModelsFactory
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.coreapi.splash.SplashVM
import timber.log.Timber

/**
 * [ICoreViewModelsFactory] using companion object for providing Api modules singletons for created view models
 *
 */
abstract class CoreViewModelsFactory(override val coreMainActivity: CoreMainActivity) : ICoreViewModelsFactory {
    override val networkStatus get() = getNetworkStatus(coreMainActivity.application)

    private fun getCoreMainVm(): CoreMainVM =
        CoreMainVmImpl(
            coreMainActivity.application,
            singletonAds(),
            singletonPayments(),
            networkStatus,
            singletonConsent(),
            appConfig
        )

    private fun getSplashVm(): SplashVM = CoreSplashVMImpl(
        coreMainActivity.application,
        networkStatus = networkStatus,
        ads = singletonAds(),
        gdprConsent = singletonConsent(),
        noAdsPurchases = singletonPayments(),
        appConfig = appConfig
    )

    private fun singletonConsent() = getGdprConsent { gdprConsent }

    private fun singletonPayments() = getPayments { payments }

    private fun singletonAds() = getAds { ads }

    /**
     * Creates a new instance of the given [SplashVM] or [CoreMainVM]
     *
     * @param modelClass a {@code Class} whose instance is requested
     * @param T The type parameter for the ViewModel.
     * @return a newly created [SplashVM] or [CoreMainVM]
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashVM::class.java)) return getSplashVm() as T
        if (modelClass.isAssignableFrom(CoreMainVM::class.java)) return getCoreMainVm() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    /**
     * Provides singletons for Api modules
     */
    companion object {
        @Volatile
        private var ads: IAds? = null

        @Volatile
        private var networkStatus: INetworkStatus? = null

        @Volatile
        private var gdprConsent: IGDPRConsent? = null

        @Volatile
        private var payments: IPayments? = null

        private fun getAds(constructor: () -> IAds): IAds =
            ads ?: synchronized(this) {
                ads ?: constructor().also {
                    Timber.d("$it")
                    ads = it
                }
            }

        private fun getNetworkStatus(application: Application): INetworkStatus =
            networkStatus ?: synchronized(this) { networkStatus ?: NetworkStatus(application).also { networkStatus = it } }

        private fun getGdprConsent(constructor: () -> IGDPRConsent): IGDPRConsent =
            gdprConsent ?: synchronized(this) { gdprConsent ?: constructor().also { gdprConsent = it } }

        private fun getPayments(constructor: () -> IPayments): IPayments =
            payments ?: synchronized(this) { payments ?: constructor().also { payments = it } }

        /**
         * Used to clean up ads instance after [CoreMainVM] is cleared(activity is killed)
         *
         */
        fun cleanAds() {
            Timber.d("()")
            ads = null
        }
    }
}