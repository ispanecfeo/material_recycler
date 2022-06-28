package com.example.material_3.ui.adapter

import com.example.material_3.ui.delegates.DelegateAdapterItem

data class HeaderAdapterModel(private val textHeader: String) : DelegateAdapterItem {
    override fun id(): String  = HeaderAdapterModel::class.java.toString()
    override fun content(): String = textHeader
}