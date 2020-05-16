package pl.netigen.coreapi.main

import android.content.Intent
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import pl.netigen.coreapi.payments.INoAds

/**
 * Base and by design should be only Activity in application
 *
 *
 */
interface ICoreMainActivity {
    /**
     * Indicates if we can safe perform Fragment transaction
     * as [commit()][FragmentTransaction.commit] or [popBackStack()][FragmentManager.popBackStack] and others
     * otherwise it will result with
     * [IllegalStateException][java.lang.IllegalStateException] crash
     *
     * see: [FragmentManager.isStateSaved]
     *
     * see: [stackoverflow](https://stackoverflow.com/a/44064149/3442734)
     */
    val canCommitFragments: Boolean

    /**
     * Returns if no ads in-app purchase is active or inactive, its current value of [INoAds.noAdsActive],
     * and should be used only when observing this value is hard to implement
     *
     */
    val noAdsActive: Boolean

    /**
     * Indicates if [CoreSplashFragment][pl.netigen.core.splash.CoreSplashFragment] is visible to the user
     */
    val splashActive: Boolean

    /**
     *
     */
    val viewModelFactory: ICoreViewModelsFactory
    val coreMainVM: ICoreMainVM

    fun onSplashOpened()

    fun onSplashClosed()

    fun showGdprPopUp()

    @CallSuper
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    fun hideAds()
    fun showAds()
    fun onNoAdsChanged(noAdsActive: Boolean)
}