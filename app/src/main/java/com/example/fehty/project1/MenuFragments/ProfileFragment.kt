package com.example.fehty.project1.MenuFragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Activity.MainActivity
import com.example.fehty.project1.R
import com.facebook.*
import com.facebook.AccessToken
import com.facebook.internal.ImageRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

@SuppressLint("ValidFragment")
class ProfileFragment(private val mainActivity: MainActivity?) : Fragment() {

    private var callbackManager = CallbackManager.Factory.create()!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setReadPermissions("email")
        loginButton.fragment = this

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        displayFacebookData()
                    }

                    override fun onCancel() {
                        underPhotoUserText.text = "Login is cancelled"
                    }

                    override fun onError(exception: FacebookException) {
                        underPhotoUserText.text = "Login Error"
                    }

                })

        displayFacebookData()

        accessTokenTracker
    }

    private var accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            when {
                currentAccessToken == null -> {
                    underPhotoUserText.text = "You have logged out"
                    userPhoto.setImageResource(android.R.color.transparent)
                }
                currentAccessToken != null -> {
                    LoginManager.getInstance().registerCallback(callbackManager,
                            object : FacebookCallback<LoginResult> {
                                override fun onSuccess(loginResult: LoginResult) {
                                    displayFacebookData()
                                }

                                override fun onCancel() {
                                    underPhotoUserText.text = "Login is cancelled"
                                }

                                override fun onError(exception: FacebookException) {
                                    underPhotoUserText.text = "Login Error"
                                }
                            })
                    displayFacebookData()
                }
            }
        }
    }

    private fun displayFacebookData() {
        if (AccessToken.getCurrentAccessToken() != null) {
            val request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken()) { me, response ->
                if (AccessToken.getCurrentAccessToken() != null) {
                    if (me != null) {
                        val profileImageUrl = ImageRequest.getProfilePictureUri(me.optString("id"), 500, 500)
                        Picasso.get().load(profileImageUrl).into(userPhoto)
                        underPhotoUserText.text = response.jsonObject.getString("name")
                    }
                }
            }
            GraphRequest.executeBatchAsync(request)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            exitFromAdditionItems()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun exitFromAdditionItems() {
        mainActivity!!.floatingActionButton.show()
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        fragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container, ListFragment())
                ?.commit()
    }
}