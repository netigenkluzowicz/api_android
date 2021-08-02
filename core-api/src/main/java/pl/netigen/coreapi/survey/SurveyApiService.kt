package pl.netigen.coreapi.survey

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface SurveyApiService {
    @Headers("Content-Type: application/json")
    @POST("survey")
    suspend fun sendSurvey(@Body survey: SurveyData): Call<SurveyData>
}

private const val BASE_URL = "https://feedback.netigen.eu/survey"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .build()

object SurveyApi {
    val retrofitService: SurveyApiService by lazy { retrofit.create(SurveyApiService::class.java) }
}