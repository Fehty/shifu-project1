package com.example.fehty.project1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.fehty.project1.Activity.MainActivity
import com.example.fehty.project1.Interface.ApiInterface
import com.example.fehty.project1.Poco.ApiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_full_screen)

        asyncTask.execute()
    }

    private var asyncTask = @SuppressLint("StaticFieldLeak")
    object : AsyncTask<String, String, String>() {

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            val retrofit = Retrofit
                    .Builder()
                    .baseUrl("https://restcountries.eu")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterface::class.java)
                    .listApiModel()

            retrofit.enqueue(object : Callback<MutableList<ApiModel>> {
                override fun onResponse(call: Call<MutableList<ApiModel>>?, response: Response<MutableList<ApiModel>>?) {
                    goToListFragment(response!!.body()!!)
                    Log.e("*#*#*#*#*#**#", "onSuccess")
                }

                override fun onFailure(call: Call<MutableList<ApiModel>>?, t: Throwable?) {
                    Log.e("*#*#*#*#*#**#", "onFailure $t")
                }

            })

        }

        override fun doInBackground(vararg params: String?): String? {
            return null
        }
    }

    fun goToListFragment(async: MutableList<ApiModel>) {
//        val gson = Gson()
//        val type = object : TypeToken<MutableList<ApiModel>>() {}.type
//        val json = gson.toJson(async, type)
//        intent.putExtra("zxc", json)
        val intent = Intent(this@FullScreenActivity, MainActivity::class.java)
        startActivity(intent)
    }

}