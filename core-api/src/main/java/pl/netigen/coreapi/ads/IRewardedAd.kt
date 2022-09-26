package pl.netigen.coreapi.ads

/**
 * Manages rewarded ads:
 * - loading first and next ads when first is displayed
 * - reloading on load errors
 * - showing to the users
 * - provides callback about result of the reward watch user action
 *
 * Rewarded ad  allow you to reward users with in-app items for interacting with video ads, playable ads, and surveys.
 */
interface IRewardedAd : IAd {
    /**
     * Informs is ad ready to show
     */
    val isLoaded: Boolean

    /**
     * Shows rewarded ad and provides information if user should be given with reward
     *
     * @param onRewardResult Callback invoked when interaction with ad is finished, if onRewardResult is true user should be rewarded
     */
    fun showRewardedAd(onRewardResult: (Boolean) -> Unit)
}
