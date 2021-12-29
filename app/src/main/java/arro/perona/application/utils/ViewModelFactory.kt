package arro.perona.application.utils

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import arro.perona.application.ui.sub.SubViewModel
import arro.perona.application.ui.Weather.WeatherViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by Arro on 2021/12/27
 */
class ViewModelFactory(val disposable: CompositeDisposable): ViewModelProvider.Factory{

    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(WeatherViewModel::class.java) -> WeatherViewModel(disposable) as T
            modelClass.isAssignableFrom(SubViewModel::class.java) -> SubViewModel(disposable) as T
            else -> null as T
        }
    }
}