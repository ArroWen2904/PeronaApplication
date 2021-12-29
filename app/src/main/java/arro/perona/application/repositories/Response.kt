package arro.perona.application.repositories

import com.google.gson.annotations.SerializedName

/**
 * Created by Arro on 2021/12/27
 */

class ResponseWeather(
    @SerializedName("records") val records: Records
)

data class Records(
    @SerializedName("datasetDescription") val datasetDescription: String = "",
    @SerializedName("location") val locationWeather: List<LocationWeather> = listOf()
)

data class LocationWeather(
    @SerializedName("locationName") val locationName: String = "",
    @SerializedName("weatherElement") val weatherElementList: List<WeatherElement> = listOf()
)

data class WeatherElement(
    @SerializedName("elementName") val elementName: String = "",
    @SerializedName("time") val timeList: List<WeatherTime> = listOf()
)

data class WeatherTime(
    @SerializedName("startTime") val startTime: String = "",
    @SerializedName("endTime") val endTime: String = "",
    @SerializedName("parameter") val parameter: Parameter
)

data class Parameter(
    @SerializedName("parameterName") val parameterName: String = "",
    @SerializedName("parameterValue") val parameterValue: String = "",
    @SerializedName("parameterUnit") val parameterUnit: String = ""
)