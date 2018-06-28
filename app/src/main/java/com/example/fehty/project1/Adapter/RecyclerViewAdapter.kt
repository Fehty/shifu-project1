package com.example.fehty.project1.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import com.example.fehty.project1.MenuFragments.ListFragment
import com.example.fehty.project1.Poco.ItemsData
import com.example.fehty.project1.R

class RecyclerViewAdapter(var listFragment: ListFragment? = null) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var list = mutableListOf<ItemsData>()

    fun setList(list: MutableList<ItemsData>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_template_for_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.swipeMenu))
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var itemTitle = view.findViewById<TextView>(R.id.itemTitle)
        var swipeLayout = view.findViewById<SwipeLayout>(R.id.swipeLayout)!!
        var delete = view.findViewById<TextView>(R.id.delete)!!
        var edit = view.findViewById<TextView>(R.id.edit)!!
        fun bind(dataItems: ItemsData) {
            itemTitle.text = dataItems.itemTitle

            edit.setOnClickListener {
                listFragment!!.changeThisFragmentToAddItemFragment(dataItems.id.toString())
            }

            delete.setOnClickListener {
                removeItem(adapterPosition)
                listFragment!!.removeFromRealm(dataItems.id)
            }
        }
    }
}