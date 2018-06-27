package com.example.fehty.project1.MenuFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Activity.MainActivity
import com.example.fehty.project1.Poco.Poco
import com.example.fehty.project1.R
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_item.*

@SuppressLint("ValidFragment")
class AddItemFragment(var callSetValues: Boolean = false, var pocoId: String = "1") : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    private val listFragment = ListFragment()
    private val realm = Realm.getDefaultInstance()
    private val poco = Poco()
    private var nextInt = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (callSetValues) {
            setValues(pocoId)
        }
        buttonAddItem.setOnClickListener {

            if (addItemTitle.text.isNotEmpty() && addItemContent.text.isNotEmpty() && addItemLink.text.isNotEmpty()) {

                if (callSetValues) {
                    realm.executeTransaction {
                        val updateResult = realm.where(Poco::class.java).equalTo("id", pocoId.toInt()).findFirst()

                        updateResult!!.titleOfTheList = addItemTitle.text.toString()
                        updateResult.contentOfTheList = addItemContent.text.toString()
                        updateResult.linkOfTheList = addItemLink.text.toString()
                    }
                } else {
                    realm.executeTransaction {
                        val resultOfId = realm.where(Poco::class.java).max("id")
                        when (resultOfId) {
                            null -> nextInt = 1
                            else -> nextInt = resultOfId.toInt() + 1
                        }

                        poco.id = nextInt
                        poco.titleOfTheList = addItemTitle.text.toString()
                        poco.contentOfTheList = addItemContent.text.toString()
                        poco.linkOfTheList = addItemLink.text.toString()

                        realm.insertOrUpdate(poco)
                    }
                }

            }

            addItemTitle.clearFocus()
            addItemContent.clearFocus()
            addItemLink.clearFocus()

            exitFromAdditionItems()
        }

    }

    private fun setValues(pocoId: String) {
        realm.executeTransaction {
            val result = realm.where(Poco::class.java).equalTo("id", pocoId.toInt()).findAll()
            for (poco in result) {
                addItemTitle.setText(poco.titleOfTheList)
                addItemContent.setText(poco.contentOfTheList)
                addItemLink.setText(poco.linkOfTheList)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            exitFromAdditionItems()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun exitFromAdditionItems() {
        val mainActivity = activity as MainActivity
        activity!!.floatingActionButton.show()
        mainActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, listFragment)
                ?.commit()
    }

}


//                val bundle = Bundle()
//                bundle.putString("newItem", addItemText.text.toString())
//                listFragment.arguments = bundle
