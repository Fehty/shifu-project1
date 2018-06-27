package com.example.fehty.project1.MenuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Activity.MainActivity
import com.example.fehty.project1.Adapter.RecyclerViewAdapter
import com.example.fehty.project1.Poco.DataItems
import com.example.fehty.project1.Poco.Poco
import com.example.fehty.project1.R
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val adapter = RecyclerViewAdapter(this)
    private val list = arrayListOf<DataItems>()
    private val realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        //    val itemTouchHelperCallBack = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        //    ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView)
    }

    //override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
    //    adapter.removeItem(viewHolder.adapterPosition)
    //    realm.executeTransaction {
    //        //viewHolder.itemView.tag
    //        realm
    //                .where(Poco::class.java)
    //                .equalTo("titleOfTheList", viewHolder.itemView.itemTitle.text.toString())
    //                .findFirst()!!
    //                .deleteFromRealm()
    //    }
//    }

    private fun initList() {
        val results = realm.where(Poco::class.java).sort("id").findAll()

        if (results != null) {
            for (poco in results) {
                list.add(DataItems(poco.titleOfTheList.toString(), poco.id!!.toInt()))
                Log.e("*#**#*#*#**#", "${poco.titleOfTheList} ${poco.contentOfTheList} ${poco.linkOfTheList}")
            }
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        recyclerView.adapter = adapter
        adapter.setList(list)

    }

    fun changeThisFragmentTo(fragment: Fragment) {
        fragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment)
                ?.commit()
    }


    fun removeFromRealm(title: String) {
        realm.executeTransaction {
            realm
                    .where(Poco::class.java)
                    .equalTo("titleOfTheList", title)
                    .findFirst()!!
                    .deleteFromRealm()
        }
    }

    fun changeThisFragmentToAddItemFragment(pocoId: String) {
        val addItemFragment = AddItemFragment(true, pocoId)
        fragmentManager?.beginTransaction()
                ?.replace(R.id.container, addItemFragment)
                ?.commit()

        val mainActivity = activity as MainActivity
        activity!!.floatingActionButton.hide()
        mainActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}


//        val bundle: Bundle?
//        if (arguments != null) {
//            bundle = arguments
//            list.add(bundle!!.getString("newItem"))
//        }