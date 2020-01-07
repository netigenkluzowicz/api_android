package pl.netigen.coreapi.network

interface NetworkStatusChangeListener {
    fun onNetworkStatusChanged(isConnected: Boolean)
}