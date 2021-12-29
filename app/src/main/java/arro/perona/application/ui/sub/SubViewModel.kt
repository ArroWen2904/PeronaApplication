package arro.perona.application.ui.sub

import androidx.lifecycle.ViewModel
import arro.perona.application.repositories.WeatherTime
import arro.perona.application.utils.SingleLiveEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by Arro on 2021/12/29
 */
class SubViewModel(val disposable: CompositeDisposable): ViewModel() {

    val weatherTime: SingleLiveEvent<WeatherTime> = SingleLiveEvent()
}