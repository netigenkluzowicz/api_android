package pl.netigen.sampleapp.flavour

import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.hms.payments.HMSPayments

object FlavoursConst {
    fun getPaymentsImpl(coreMainActivity: CoreMainActivity): IPayments = HMSPayments(coreMainActivity)
}