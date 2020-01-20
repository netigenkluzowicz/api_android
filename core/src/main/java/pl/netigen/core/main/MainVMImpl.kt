package pl.netigen.core.main

import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.MainVM
import pl.netigen.coreapi.payments.IPayments

open class MainVMImpl(
    private val ads: IAds,
    private val payments: IPayments
) : MainVM(), IAds by ads, IPayments by payments