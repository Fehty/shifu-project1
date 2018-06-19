package com.example.fehty.project1.ItemTouchHelper

import android.support.v7.widget.RecyclerView

interface RecyclerItemTouchHelperListener {

    fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
}
