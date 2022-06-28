package com.example.material_3.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.material_3.R
import com.example.material_3.ui.delegates.DelegateAdapter

class HeaderAdapter(
    private val deleteElement: ((item: HeaderAdapterModel) -> Unit)? = null
) :
    DelegateAdapter<HeaderAdapterModel, HeaderAdapter.HeaderViewHolder>(HeaderAdapterModel::class.java) {

    inner class HeaderViewHolder(
        private val itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: HeaderAdapterModel, position: Int) {
            with(itemView) {
                findViewById<EditText>(R.id.item_description_title).setText(item.content())
                findViewById<ImageView>(R.id.img_delete).setOnClickListener {
                    deleteElement?.invoke(item)
                }
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        HeaderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_todo_list_header, parent, false)
        )


    override fun bindViewHolder(
        model: HeaderAdapterModel,
        viewHolder: HeaderViewHolder,
        position: Int
    ) {
        viewHolder.bind(model, position)
    }

}