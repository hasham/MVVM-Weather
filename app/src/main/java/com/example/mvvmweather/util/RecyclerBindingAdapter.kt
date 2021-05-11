package com.example.mvvmweather.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmweather.BR
import com.example.mvvmweather.databinding.ListItemForecastBinding
import java.util.*

/**
 * Developed by Hasham.Tahir on 10/17/2016.
 */

class RecyclerBindingAdapter<T>(
    private val holderLayout: Int,
    private val variableId: Int,
    items: AbstractList<T>?
) : RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder>() {

    private var callback: Any? = null

    constructor(holderLayout: Int, variableId: Int, items: AbstractList<T>, callback: Any?) : this(
        holderLayout,
        variableId,
        items
    ) {

        this.callback = callback
    }

    private var items: AbstractList<T>? = ArrayList()
    private var onItemClickListener: OnItemClickListener<T>? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerBindingAdapter.BindingHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(holderLayout, parent, false)
        return BindingHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerBindingAdapter.BindingHolder,
        position: Int
    ) {
        val item = items!![position]
        holder.binding.root.setOnClickListener {
            if (onItemClickListener != null)
                onItemClickListener!!.onItemClick(position, item)
        }

        holder.binding.setVariable(variableId, item)

//        if (callback != null) {
//
//            holder.binding.setVariable(BR.callback, callback)
//        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        this.onItemClickListener = onItemClickListener
    }

    fun updateList(list: AbstractList<T>) {
        this.items = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener<T> {
        fun onItemClick(position: Int, item: T)
    }

    class BindingHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding: ListItemForecastBinding = DataBindingUtil.bind(v)!!

    }
}
