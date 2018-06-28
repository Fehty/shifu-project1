package com.example.fehty.project1.MenuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Activity.MainActivity
import com.example.fehty.project1.Adapter.RecyclerViewAdapter
import com.example.fehty.project1.Poco.ItemsData
import com.example.fehty.project1.Poco.RealmModel
import com.example.fehty.project1.R
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val adapter = RecyclerViewAdapter(this)
    private val list = arrayListOf<ItemsData>()
    private val realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }


    private fun initList() {
        val realmBase = realm.where(RealmModel::class.java).sort("id").findAll()
        realmBase?.forEach {
            list.add(ItemsData(it.titleOfTheList.toString(), it.id!!.toInt()))
        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

        adapter.setList(list)

    }

    fun removeFromRealm(itemRealmId: Int) {
        realm.executeTransaction {
            realm
                    .where(RealmModel::class.java)
                    .equalTo("id", itemRealmId)
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