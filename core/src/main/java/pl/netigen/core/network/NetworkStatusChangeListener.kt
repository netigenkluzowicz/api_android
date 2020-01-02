package pl.netigen.core.network

interface NetworkStatusChangeListener {
    fun onNetworkStatusChanged(isConnected: Boolean)
}