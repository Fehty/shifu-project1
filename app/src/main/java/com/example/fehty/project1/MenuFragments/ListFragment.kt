package com.example.fehty.project1.MenuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Adapter.RecyclerViewAdapter
import com.example.fehty.project1.ItemTouchHelper.RecyclerItemTouchHelper
import com.example.fehty.project1.ItemTouchHelper.RecyclerItemTouchHelperListener
import com.example.fehty.project1.Poco.Poco
import com.example.fehty.project1.R
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_template_for_list.view.*

class ListFragment : Fragment(), RecyclerItemTouchHelperListener {

    private val adapter = RecyclerViewAdapter(this)
    //  private val list = arrayListOf("Russia", "England", "Spain", "Canada", "USA", "Egypt", "Australia", "Germany", "Portugal", "Brazil")
    private val list = arrayListOf<String>()
    private val realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()

        val itemTouchHelperCallBack = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        adapter.removeItem(viewHolder.adapterPosition)
        realm.executeTransaction {
            //viewHolder.itemView.tag
            realm
                    .where(Poco::class.java)
                    .equalTo("itemOfTheList", viewHolder.itemView.item.text.toString())
                    .findFirst()!!
                    .deleteFromRealm()
        }
    }

    fun goToBackFragment() {
        fragmentManager?.beginTransaction()
                ?.replace(R.id.container, BackFragment())
                ?.commit()
    }

    private fun initList() {
        val results = realm.where(Poco::class.java).findAll()

        if (results != null) {
            for (poco in results) {
                list.add(poco.itemOfTheList.toString())
            }
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }


//        val bundle: Bundle?
//        if (arguments != null) {
//            bundle = arguments
//            list.add(bundle!!.getString("newItem"))
//        }

        recyclerView.adapter = adapter
        adapter.setList(list)
    }
}
