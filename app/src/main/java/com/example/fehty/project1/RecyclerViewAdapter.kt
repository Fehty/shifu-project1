package com.example.fehty.project1

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView


class RecyclerViewAdapter(var mainActivity: MainActivity) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var list = mutableListOf<String>()

    fun setList(list: MutableList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_template_for_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private var item = view.findViewById<TextView>(R.id.item)
        var viewBackground = view.findViewById<RelativeLayout>(R.id.viewBackground)!!
        var viewForeground = view.findViewById<RelativeLayout>(R.id.viewForeground)!!

        fun bind(dataItem: String) {
            item.text = dataItem

            view.setOnClickListener {
                mainActivity.goToActivity()
            }

        }
    }
}
