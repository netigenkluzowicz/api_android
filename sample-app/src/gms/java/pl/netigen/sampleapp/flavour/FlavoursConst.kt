package pl.netigen.sampleapp.flavour

import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.gms.payments.GMSPayments

object FlavoursConst {
    fun getPaymentsImpl(coreMainActivity: CoreMainActivity): IPayments = GMSPayments(coreMainActivity.application)
}