package arro.perona.application.ui.Weather

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import arro.perona.application.R
import arro.perona.application.utils.ViewModelFactory
import arro.perona.application.utils.WeatherAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel
    val disposable: CompositeDisposable = CompositeDisposable()
    private val TAG = "MainActivity"
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val viewModelFactory: ViewModelFactory = ViewModelFactory(disposable)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)

        setObserve()

        viewModel.getWeather()

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("firstEnter", true))
            sharedPreferences.edit().putBoolean("firstEnter", false).apply()
        else{
            viewModel.toastMessage.value = "歡迎回來"
        }
    }

    private fun setObserve() {
        viewModel.weatherElement.observe(this, Observer {

            var weatherAdapter = WeatherAdapter(it.timeList)
            val layoutManager = LinearLayoutManager(this@WeatherActivity)
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            rv_weather.adapter = weatherAdapter
            rv_weather.layoutManager = layoutManager
        })

        viewModel.toastMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }
}