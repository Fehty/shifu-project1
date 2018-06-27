package com.example.fehty.project1.Poco

import io.realm.RealmObject

open class Poco : RealmObject() {
    var id: Int? = null
    var titleOfTheList: String? = null
    var contentOfTheList: String? = null
    var linkOfTheList: String? = null
}