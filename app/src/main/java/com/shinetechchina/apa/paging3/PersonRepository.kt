package com.shinetechchina.apa.paging3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created by Arthur on 2021/10/25.
 */
class PersonRepository {

    fun search(
        req: ReqBody,
        viewModelScope: CoroutineScope
    ): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = {
                PersonPagingSource(
                    req,
                )
            }
        ).flow.cachedIn(viewModelScope)
    }
}