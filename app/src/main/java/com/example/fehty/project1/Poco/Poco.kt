package com.example.fehty.project1.Poco

import io.realm.RealmObject

open class Poco : RealmObject() {
    var itemOfTheList: String? = null
    var id : Int? = null
}