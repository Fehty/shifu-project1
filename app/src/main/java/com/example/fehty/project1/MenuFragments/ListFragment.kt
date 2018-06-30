package com.example.fehty.project1.MenuFragments

import android.annotation.SuppressLint
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
import com.example.fehty.project1.Interface.ApiInterface
import com.example.fehty.project1.Poco.ApiModel
import com.example.fehty.project1.Poco.RealmModel
import com.example.fehty.project1.R
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("ValidFragment")
class ListFragment(var async: MutableList<ApiModel> = mutableListOf()) : Fragment() {

    private val list = mutableListOf<ApiModel>()
    private val realm = Realm.getDefaultInstance()
    private val realmModel = RealmModel()
    val adapter = RecyclerViewAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {

//        val realmBase = realm.where(RealmModel::class.java).sort("id").findAll()
//        realmBase?.forEach {
//            list.add(ItemsData(it.titleOfTheList.toString(), it.id!!.toInt()))
//        }

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

         adapter.setList(async)

         loadData()

        //    adapter.setList(list)

    }

    fun loadData() {

        val retrofit = Retrofit
                .Builder()
                .baseUrl("https://restcountries.eu")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
                .listApiModel()

        retrofit.enqueue(object : Callback<MutableList<ApiModel>> {
            override fun onResponse(call: Call<MutableList<ApiModel>>?, response: Response<MutableList<ApiModel>>?) {
                adapter.setList(response!!.body()!!)
                Log.e("*#*#*#*#*#**#", "onSuccess")
            }

            override fun onFailure(call: Call<MutableList<ApiModel>>?, t: Throwable?) {
                Log.e("*#*#*#*#*#**#", "onFailure $t")
            }

        })
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