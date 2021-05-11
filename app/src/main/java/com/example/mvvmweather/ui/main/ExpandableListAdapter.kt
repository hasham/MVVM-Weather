package com.example.mvvmweather.ui.main

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmweather.data.models.Daily
import com.example.mvvmweather.databinding.ListItemForecastBinding
import java.util.*


class ExpandableListAdapter(
    private val holderLayout: Int,
    private val variableId: Int,
    items: AbstractList<Daily>?
) : RecyclerView.Adapter<ExpandableListAdapter.WeatherBindingHolder>() {

    private var items: AbstractList<Daily>? = ArrayList()

    init {
        this.items = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherBindingHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(holderLayout, parent, false)
        return WeatherBindingHolder(v)
    }

    override fun onBindViewHolder(
        holder: WeatherBindingHolder,
        position: Int
    ) {
        val item = items!![position]
        holder.binding.root.setOnClickListener {
            // collapse or expand the item
            if (item.expanded) {
                item.expanded = false
//                slideUp(holder.binding.layoutExpanded)
                holder.binding.layoutExpanded.visibility = View.GONE
            } else {
                item.expanded = true
//                slideDown(holder.binding.layoutExpanded)
                holder.binding.layoutExpanded.visibility = View.VISIBLE
            }
        }

        holder.binding.setVariable(variableId, item)

    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun updateList(list: AbstractList<Daily>) {
        this.items = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: Daily)
    }

    class WeatherBindingHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ListItemForecastBinding = DataBindingUtil.bind(v)!!

    }


    private fun slideUp(view: View) {

        val animate = TranslateAnimation(
            0f,
            0f,
            0f,
            -250f
        ) // toYDelta
        animate.duration = 5000
        animate.fillAfter = true

        Handler().postDelayed(Runnable {
            view.clearAnimation()
            view.visibility = View.GONE
        }, animate.duration)

        view.startAnimation(animate)
    }


    private fun slideDown(view: View) {

        val animate = TranslateAnimation(
            0f,
            0f,
            -250f,
            0f
        ) // toYDelta
        animate.duration = 5000
        animate.fillAfter = true
        view.visibility = View.VISIBLE
        view.startAnimation(animate)

    }

    private fun getViewHeight(view: View): Int {
        val widthSpec = MeasureSpec.makeMeasureSpec(view.width, MeasureSpec.EXACTLY)
        val heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        view.measure(widthSpec, heightSpec)
        return view.measuredHeight
    }
}