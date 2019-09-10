package pl.netigen.cross_ads

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PromotedAppViewModel(application: Application) : AndroidViewModel(application) {

    private val crossAdsPreferences = PromotedAppPrefHelper(getApplication())

    fun getPromotedIconLiveData(packageName: String): LiveData<PromotedAppModel?> {
        return if (crossAdsPreferences.shouldUpdatePromotedIcon()) {
            liveData(Dispatchers.IO) {
                val promotedApp = getPromotedApplication(packageName)
                emit(promotedApp)
                crossAdsPreferences.updatePromotedApp(promotedApp)
            }
        } else {
            val promotedIcon = MutableLiveData<PromotedAppModel>()
            promotedIcon.value = crossAdsPreferences.getSavedCrossAdModel()
            promotedIcon
        }
    }

    private fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    private fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.readTimeout(15, TimeUnit.SECONDS)
        return client.build()
    }

    private val webservice by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(providesOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .build()
                .create(PromotedAppWebservice::class.java)
    }

    private suspend fun getPromotedApplication(packageName: String): PromotedAppModel? {
        return try {
            webservice.getPromoteApplicationAsync(packageName)
        } catch (exception: Exception) {
            Log.e("PromotedAppViewModel", "getPromotedApplication: error: ${exception.message} " +
                    "\nstack: ${exception.stackTrace.joinToString(separator = "\n")}")
            null
        }
    }

    public fun preparePromotedIconPath(promotedIcon: PromotedAppModel): String {
        return PromotedAppViewModel.BASE_URL + promotedIcon.iconLink!!
    }

    companion object {
        const val BASE_URL = "https://promote-btn.netigen.pl/"
    }
}