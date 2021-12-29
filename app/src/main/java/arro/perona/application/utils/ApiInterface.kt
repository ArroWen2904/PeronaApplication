package arro.perona.application.utils

import arro.perona.application.repositories.ResponseWeather
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Arro on 2021/12/27
 */
interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("F-C0032-001")
    fun getWeather(
        @Query("Authorization") account: String = "CWB-79107998-8362-486B-8CC2-E0080E75A234",
        @Query("format") format: String = "JSON",
        @Query("locationName") locationName: Array<String> = arrayOf("臺北市"),
        @Query("sort") time: String = "time"
    ): Single<ResponseWeather>
}