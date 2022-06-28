package com.example.material_3.ui.adapter

import com.example.material_3.ui.delegates.DelegateAdapterItem

data class ContentAdapterModel(val title: String, private val textContent:String ):DelegateAdapterItem {
    override fun id(): String = ContentAdapterModel::class.java.toString()
    override fun content(): String = textContent
}