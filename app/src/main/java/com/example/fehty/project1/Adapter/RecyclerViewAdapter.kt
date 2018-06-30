package com.example.fehty.project1.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import com.example.fehty.project1.MenuFragments.ListFragment
import com.example.fehty.project1.Poco.ApiModel
import com.example.fehty.project1.Poco.RealmModel
import com.example.fehty.project1.R
import io.realm.Realm

class RecyclerViewAdapter(var listFragment: ListFragment? = null) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    //private var list = mutableListOf<ItemsData>()
    private var list = mutableListOf<ApiModel>()
    private val realmModel = RealmModel()
    private val realm = Realm.getDefaultInstance()

    fun setList(list: MutableList<ApiModel>) {
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
        fun bind(dataItems: ApiModel) {
            //      itemTitle.text = dataItems.itemTitle
            itemTitle.text = dataItems.name

            realm.executeTransaction {
                realmModel.titleOfTheList = dataItems.name
                realm.insertOrUpdate(realmModel)
            }

            checkRealmDB()

            edit.setOnClickListener {
                //  listFragment!!.changeThisFragmentToAddItemFragment(dataItems.id.toString())
            }

            delete.setOnClickListener {
                removeItem(adapterPosition)
                // listFragment!!.removeFromRealm(dataItems.id)
            }
        }

        fun checkRealmDB() {
            val realmBase = realm.where(RealmModel::class.java).findAll()
            realmBase?.forEach {
                Log.e("*#*#*#*#*#**#", "${it.titleOfTheList}")
            }
        }
    }
}