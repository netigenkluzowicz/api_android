package pl.netigen.core.network

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress


class NetworkStatus : INetworkStatus {
    private val networkStatusChangeListeners: MutableList<NetworkStatusChangeListener> = mutableListOf()
    override var lastKnownStatus: Boolean = true
        private set

    override suspend fun requestNetworkState(): Boolean = checkConnection()

    private fun checkConnection(): Boolean {
        return try {
            val sock = Socket()
            val socketAddress: SocketAddress = InetSocketAddress(GOOGLE_DNS_HOSTNAME, GOOGLE_DNS_PORT)
            sock.connect(socketAddress, CONNECTION_CHECK_TIMEOUT_MS)
            sock.close()
            lastKnownStatus = true
            postValue(true)
            true
        } catch (e: IOException) {
            postValue(false)
            false
        }
    }

    private fun postValue(status: Boolean) {
        for (networkStatusChangeListener in networkStatusChangeListeners) {
            networkStatusChangeListener.onNetworkStatusChanged(status)
        }
    }

    override fun addNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener) {
        networkStatusChangeListeners.add(networkStatusChangeListener)
    }

    override fun removeNetworkStatusChangeListener(networkStatusChangeListener: NetworkStatusChangeListener) {
        networkStatusChangeListeners.remove(networkStatusChangeListener)
    }

    companion object {
        const val GOOGLE_DNS_HOSTNAME = "8.8.8.8"
        const val CONNECTION_CHECK_TIMEOUT_MS = 1500
        const val GOOGLE_DNS_PORT = 53
    }

}