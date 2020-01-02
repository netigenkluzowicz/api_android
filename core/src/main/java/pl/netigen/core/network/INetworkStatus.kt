package pl.netigen.core.network

interface INetworkStatus {
    val isConnected: Boolean
    fun addNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener)
    fun removeNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener)
}