package pl.netigen.core.network

import android.app.Application
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.network.NetworkStatusChangeListener
import timber.log.Timber
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress


class NetworkStatus(private val application: Application) : INetworkStatus {
    private val networkStatusChangeListeners: MutableList<NetworkStatusChangeListener> = mutableListOf()
    override val isConnectedOrConnecting: Boolean
        get() {
            val connectivityManager = getSystemService(application, ConnectivityManager::class.java)
            @Suppress("DEPRECATION")
            val isConnectedOrConnecting = connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
            if (!isConnectedOrConnecting) {
                postValue(false)
            }
            return isConnectedOrConnecting
        }

    override suspend fun requestNetworkState(): Boolean = checkConnection()

    private fun checkConnection(): Boolean {
        if (!isConnectedOrConnecting) {
            postValue(false)
            return false
        }
        return try {
            val sock = Socket()
            val socketAddress: SocketAddress = InetSocketAddress(
                GOOGLE_DNS_HOSTNAME,
                GOOGLE_DNS_PORT
            )
            sock.connect(socketAddress, CONNECTION_CHECK_TIMEOUT_MS)
            sock.close()
            postValue(true)
            true
        } catch (e: IOException) {
            Timber.e(e)
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