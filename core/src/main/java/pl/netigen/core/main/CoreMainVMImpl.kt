package pl.netigen.core.main

import pl.netigen.coreapi.ads.IAds
import pl.netigen.coreapi.main.CoreMainVM
import pl.netigen.coreapi.payments.IPayments

open class CoreMainVMImpl(
    private val ads: IAds,
    private val payments: IPayments
) : CoreMainVM(), IAds by ads, IPayments by payments