package pl.netigen.coreapi.main

import android.os.Bundle
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.network.INetworkStatus
import pl.netigen.coreapi.payments.IPayments

interface ICoreMainVM : IPayments, IAds, INetworkStatus {
    fun onSavedStateRestored(savedInstanceState: Bundle)
}