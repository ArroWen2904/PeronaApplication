package arro.perona.application.utils

import arro.perona.application.repositories.ResponseWeather
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Arro on 2021/12/27
 */
object ApiUtil {

    private const val DOMAIN: String = "opendata.cwb.gov.tw"
    private var apiInterface: ApiInterface = getApiInterface()

    private fun getApiInterface(): ApiInterface {
        return Retrofit.Builder()
            .client(OkHttpUtil.client)
            .baseUrl(getHostUri())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    private fun getHostUri(): String {
        return "https://$DOMAIN/api/v1/rest/datastore/"
    }

    fun ApiGetWeather(): Single<ResponseWeather> {
        return apiInterface.getWeather()
    }
}