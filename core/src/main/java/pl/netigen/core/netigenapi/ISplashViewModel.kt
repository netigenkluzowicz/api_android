package pl.netigen.core.netigenapi

interface ISplashViewModel {
    fun start()

    enum class SplashState {
        LOADING, LOADING_FIRST_LAUNCH, GDPR_POP_UP, FINISHED
    }
}