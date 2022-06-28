package com.example.material_3.ui.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.material_3.ui.adapter.ContentAdapterModel
import com.example.material_3.ui.adapter.HeaderAdapterModel
import com.example.material_3.ui.delegates.DelegateAdapterItem
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel: ViewModel() {

    private  var _copyList: MutableList<DelegateAdapterItem>? = null
    private var _listItems = MutableLiveData<MutableList<DelegateAdapterItem>>()
    val listItems : LiveData<MutableList<DelegateAdapterItem>>
        get() = _listItems!!

    init {
        setupListAdapter()
    }


    private fun setupListAdapter() {

        _listItems.value = mutableListOf(
            HeaderAdapterModel("По работе"),
            ContentAdapterModel("Разобраться с ORM", "Изучить ORM для Django 3. " +
                    "просмотреть все ключевые моменты. Попробовать реализовать работу с БД через" +
                    "ORM"),
            ContentAdapterModel("Изучить PostgreSQL", "Изучить postgres, сравнить с MS SQL " +
                    "сравнить работу уровней транзакционных блокировок"),
            HeaderAdapterModel("По учебе"),
            ContentAdapterModel("Выполнить ДЗ", "Постараться разобраться и сделать все" +
                    "домашние работы, которые остались по курсу MD"),
            HeaderAdapterModel("По дому"),
            ContentAdapterModel("Сходить в ТЦ", "Необходимо преобрести инструменты для " +
                    "ремонта кухни и спальной")
        )

    }

    fun updateList(item: DelegateAdapterItem) {

        _copyList = mutableListOf()

        _listItems.value?.forEach {
            _copyList?.add(it)
        }

        _copyList?.remove(item)

        _listItems.postValue(
            _copyList
        )
    }

}