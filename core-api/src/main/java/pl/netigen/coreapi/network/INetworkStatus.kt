package pl.netigen.coreapi.network

interface INetworkStatus {
    val isConnectedOrConnecting: Boolean
    suspend fun requestNetworkState(): Boolean
    fun addNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener)
    fun removeNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener)
}