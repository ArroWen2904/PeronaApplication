package arro.perona.application.ui.Weather

import android.util.Log
import androidx.lifecycle.ViewModel
import arro.perona.application.repositories.ResponseWeather
import arro.perona.application.repositories.WeatherElement
import arro.perona.application.utils.ApiUtil
import arro.perona.application.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Arro on 2021/12/27
 */
class WeatherViewModel(val disposable: CompositeDisposable): ViewModel() {

    private val TAG = "MainViewModel"
    val weatherElement: SingleLiveEvent<WeatherElement> = SingleLiveEvent()
    val toastMessage: SingleLiveEvent<String> = SingleLiveEvent()

    fun getWeather(){
        disposable.add(
            ApiUtil.ApiGetWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ResponseWeather>(){
                    override fun onSuccess(t: ResponseWeather?) {
                        Log.w(TAG, "onSuccess")
                        t?.records?.locationWeather?.first()?.weatherElementList?.forEach { it ->
                            when(it.elementName){
                                "MinT" -> weatherElement.value = it
                            }
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Log.w(TAG, "onError")
                        toastMessage.value = "請檢察網路是否正常連線"
                    }
                })
        )
    }
}