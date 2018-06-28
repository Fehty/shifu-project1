//package com.example.fehty.project1.ItemTouchHelper
//
//import android.support.v7.widget.RecyclerView
//
//interface RecyclerItemTouchHelperListener {
//
//    fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
//}


//                val bundle = Bundle()
//                bundle.putString("newItem", addItemText.text.toString())
//                listFragment.arguments = bundle


//private fun setFacebookData(loginResult: LoginResult) {
//    val request = GraphRequest.newMeRequest(loginResult.accessToken) { _, response ->
//        try {
//            Log.e("Response", response.toString())
//
//            val email = response.jsonObject.getString("email")
//            val firstName = response.jsonObject.getString("first_name")
//            val lastName = response.jsonObject.getString("last_name")
//            val gender = response.jsonObject.getString("gender")
//
//            val profile = Profile.getCurrentProfile()
//            val id = profile.id
//            val link = profile.linkUri.toString()
//            Log.e("Link", link)
//            if (Profile.getCurrentProfile() != null) {
//                Log.e("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200))
//            }
//
//            Log.e("Login" + "Email", email)
//            Log.e("Login" + "FirstName", firstName)
//            Log.e("Login" + "LastName", lastName)
//            Log.e("Login" + "Gender", gender)
//
//
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//    }
//
//    val parameters = Bundle()
//    parameters.putString("fields", "id,email,first_name,last_name,gender")
//    request.parameters = parameters
//    request.executeAsync()
//}

//        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(result: LoginResult?) {
//                textView.text = "Login is succeed ${result!!.accessToken.userId} ${result.accessToken.token}"
//            }
//
//            override fun onCancel() {
//                textView.text = "Login is cancelled"
//            }
//
//            override fun onError(error: FacebookException?) {
//                textView.text = "Login Error"
//            }
//
//        })