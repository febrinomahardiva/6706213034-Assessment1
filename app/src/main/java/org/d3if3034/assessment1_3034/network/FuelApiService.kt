package org.d3if3034.assessment1_3034.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3034.assessment1_3034.model.Jenis
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/febrinomahardiva/6706213034-Assessment1/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FuelApiService {
    @GET("fuel_json")
    suspend fun getFuel(): List<Jenis>
}

object FuelApi {
    val service: FuelApiService by lazy {
        retrofit.create(FuelApiService::class.java)
    }

    fun getFuelUrl(imageResId: String): String {
        return "$BASE_URL$imageResId.png"
    }

enum class ApiStatus { LOADING, SUCCESS, FAILED }
}