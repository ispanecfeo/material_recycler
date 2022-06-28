package com.example.material_3.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.material_3.R
import com.example.material_3.ui.adapter.ContentAdapter
import com.example.material_3.ui.adapter.HeaderAdapter
import com.example.material_3.ui.delegates.CompositeAdapter
import com.example.material_3.ui.model.MainViewModel


class ToDoListFragment : Fragment(R.layout.fragment_todo_list) {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = ToDoListFragment()
    }

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(HeaderAdapter { item ->
                viewModel.updateList(item)
            })
            .add(ContentAdapter { item ->
                viewModel.updateList(item)
            })
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)

        val helper = ItemTouchHelper(ItemTouchHelperCallBack({
            compositeAdapter.notifyItemChanged(it)
        }, { from, to ->
            with(compositeAdapter) {
                notifyItemMoved(from, to)
                notifyItemChanged(from)
                notifyItemChanged(to)
            }
        }))

        helper.attachToRecyclerView(recycler)

        recycler.adapter = compositeAdapter
        recycler.addItemDecoration(ListItemDecorator())

        viewModel.listItems.observe(viewLifecycleOwner) {
            compositeAdapter.submitList(it)
        }

    }

}