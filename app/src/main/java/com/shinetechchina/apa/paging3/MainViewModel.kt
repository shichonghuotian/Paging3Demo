package com.shinetechchina.apa.paging3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by Arthur on 2021/10/25.
 */
class MainViewModel: ViewModel() {

    val repository: PersonRepository = PersonRepository()
    private val _items =  MutableLiveData<PagingData<PersonUiModel>>()

    val items: LiveData<PagingData<PersonUiModel>>
        get() = _items

    val reqBody = ReqBody(RequestType.Boy)

    init {


        viewModelScope.launch {
            repository.search(reqBody, viewModelScope).map {
                it.map {


                    PersonUiModel.PersonModel(it.name) as PersonUiModel
                }
            }.collectLatest {

                _items.postValue(it)
            }

        }
    }

    fun loadMore() {

        //
        reqBody.type = RequestType.Girl
    }
}