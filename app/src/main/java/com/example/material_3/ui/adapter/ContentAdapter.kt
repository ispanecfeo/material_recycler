package com.example.material_3.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.material_3.R
import com.example.material_3.databinding.RecyclerTodoListDataBinding
import com.example.material_3.ui.delegates.DelegateAdapter

class ContentAdapter(
    private val deleteElement: ((item: ContentAdapterModel) -> Unit)? = null
) :
    DelegateAdapter<ContentAdapterModel, ContentAdapter.ContentViewHolder>(ContentAdapterModel::class.java) {

    inner class ContentViewHolder(
        private val itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ContentAdapterModel, position: Int) {
            with(itemView) {
                findViewById<EditText>(R.id.item_description_title).setText(item.title)
                findViewById<EditText>(R.id.item_description).setText(item.content())
                findViewById<ImageView>(R.id.img_delete).setOnClickListener {
                    deleteElement?.invoke(item)
                }
            }

            val binding = RecyclerTodoListDataBinding.bind(itemView)

            var isTextVisible = false

            binding.btnClip.setOnClickListener {

                isTextVisible = isTextVisible.not()

                TransitionManager.beginDelayedTransition(
                    binding.transactionContainer,
                    AutoTransition()
                )

                binding.expandingView.visibility = if (isTextVisible) View.VISIBLE else View.GONE

                with(binding.btnClip) {
                    if (isTextVisible) {
                        setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
                    } else {
                        setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                    }
                }

            }

        }

    }


    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ContentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_todo_list_data, parent, false)
        )


    override fun bindViewHolder(
        model: ContentAdapterModel,
        viewHolder: ContentViewHolder,
        position: Int
    ) {
        viewHolder.bind(model, position)
    }
}