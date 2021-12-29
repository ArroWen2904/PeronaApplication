package arro.perona.application.ui.sub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import arro.perona.application.R
import arro.perona.application.databinding.ActivitySubBinding
import arro.perona.application.repositories.Parameter
import arro.perona.application.repositories.WeatherTime
import arro.perona.application.utils.ViewModelFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding
    private lateinit var viewModel: SubViewModel
    val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub)

        val viewModelFactory: ViewModelFactory = ViewModelFactory(disposable)
        viewModel = ViewModelProvider( this, viewModelFactory).get(SubViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val parameter = Parameter(parameterName = intent.getStringExtra("parameterName") ?: "", parameterUnit = intent.getStringExtra("parameterUnit") ?: "")
        val weatherTime = WeatherTime(intent.getStringExtra("startTime") ?: "", intent.getStringExtra("endTime") ?: "", parameter)

        viewModel.weatherTime.value = weatherTime
    }
}