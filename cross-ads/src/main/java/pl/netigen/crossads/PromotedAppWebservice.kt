package pl.netigen.cross_ads

import retrofit2.http.GET
import retrofit2.http.Query

interface PromotedAppWebservice {

    @GET("http://promote-btn.netigen.pl/api/v1/apps/check-promote")
    suspend fun getPromoteApplicationAsync(@Query("package_name") packageName: String): PromotedAppModel
}
