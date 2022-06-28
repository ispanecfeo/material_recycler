package com.example.material_3.ui.delegates

import androidx.recyclerview.widget.DiffUtil

class DelegateAdapterItemCallback: DiffUtil.ItemCallback<DelegateAdapterItem>() {
    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean =
        oldItem::class == newItem::class && oldItem.id() == newItem.id()


    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean = oldItem.content() == newItem.content()



}