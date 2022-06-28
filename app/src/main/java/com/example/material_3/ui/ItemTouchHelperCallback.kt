package com.example.material_3.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallBack(
    val onItemSwiped: (position: Int) -> Unit,
    val onItemsMoved: (from: Int, to: Int) -> Unit
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipe = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(drag, swipe)
    }

    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun isLongPressDragEnabled(): Boolean = true

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {

        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        onItemsMoved(from, to)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        val position = viewHolder.adapterPosition

        onItemSwiped(position)
    }


}