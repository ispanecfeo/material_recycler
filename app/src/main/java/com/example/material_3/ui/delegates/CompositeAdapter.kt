package com.example.material_3.ui.delegates

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
class CompositeAdapter(
    private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(DelegateAdapterItemCallback()) {

    class Builder {

        private var count: Int = 0
        private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>> =
            SparseArray()

        fun add(delegateAdapter: DelegateAdapter<out DelegateAdapterItem, *>) : Builder {
            delegates.put(count++, delegateAdapter as DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>)
            return this
        }

        fun build():CompositeAdapter {
            return CompositeAdapter(delegates)
        }
    }

    override fun getItemViewType(position: Int): Int {
        for (i in 0 until delegates.size()) {
            if (delegates[i].modelClass == getItem(position).javaClass)
                return delegates.keyAt(i)
        }
        throw NullPointerException("Can not get viewType for position $position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].createViewHolder(parent)



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delegateAdapter = delegates[getItemViewType(position)]
        delegateAdapter.bindViewHolder(getItem(position), holder, holder.adapterPosition)
    }

}