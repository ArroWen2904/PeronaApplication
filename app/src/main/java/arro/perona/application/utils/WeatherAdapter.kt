package arro.perona.application.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import arro.perona.application.R
import arro.perona.application.repositories.WeatherTime
import arro.perona.application.ui.sub.SubActivity
import kotlinx.android.synthetic.main.rv_item_weather.view.*

/**
 * Created by Arro on 2021/12/28
 */
class WeatherAdapter(val weatherTimeList: List<WeatherTime> = listOf() ): RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        return when(viewType){
            0 -> WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_weather, parent, false))
            else -> WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_image, parent, false))
        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

        when(holder.itemViewType){
            0 -> {
                val index = position / 2 + position % 2
                val weatherTime = weatherTimeList[index]
                holder.itemView.tv_startTime.text = weatherTime.startTime
                holder.itemView.tv_endTime.text = weatherTime.endTime
                holder.itemView.tv_parameter.text = weatherTime.parameter.parameterName + weatherTime.parameter.parameterUnit

                holder.itemView.root.setOnClickListener {
                    val intent = Intent(holder.itemView.context, SubActivity::class.java)
                    intent.putExtra("startTime", weatherTime.startTime)
                    intent.putExtra("endTime", weatherTime.endTime)
                    intent.putExtra("parameterName", weatherTime.parameter.parameterName)
                    intent.putExtra("parameterUnit", weatherTime.parameter.parameterUnit)
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return weatherTimeList.size * 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) 0 else 1
    }
}

class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)