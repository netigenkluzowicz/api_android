package pl.netigen.sampleapp.flavour

import android.app.Application
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.gms.payments.GMSPayments

object FlavoursConst {
    fun getPaymentsImpl(application: Application): IPayments = GMSPayments(application)
}