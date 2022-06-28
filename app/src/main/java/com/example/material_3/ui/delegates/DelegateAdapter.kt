package com.example.material_3.ui.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

abstract class DelegateAdapter<M, VH: RecyclerView.ViewHolder>(val modelClass: Class<M>) {
    abstract fun createViewHolder(parent:ViewGroup) : RecyclerView.ViewHolder
    abstract fun bindViewHolder(model: M, viewHolder: VH, position: Int)
}