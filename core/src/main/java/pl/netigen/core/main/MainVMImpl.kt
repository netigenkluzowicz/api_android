package pl.netigen.core.main

import androidx.lifecycle.MutableLiveData
import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.MainVM
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.coreapi.payments.Payments

open class MainVMImpl(
    private val ads: IAds,
    private val payments: Payments
) : MainVM(), IAds by ads, IPayments by payments {
    override val showSplash: MutableLiveData<Boolean> = MutableLiveData(true)
}