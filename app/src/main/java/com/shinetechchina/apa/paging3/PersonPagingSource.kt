package com.shinetechchina.apa.paging3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

/**
 * Created by Arthur on 2021/10/25.
 */
private const val Start_Index = 0

//type类型不同， 可以请求不同的数据
class PersonPagingSource(val reqBody: ReqBody) : PagingSource<Int, Person>() {

    var bodySize: Int = 0

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        val position = params.key ?: Start_Index

        val loadSize = params.loadSize
        Log.e("wy", "load")

        return try {


            if (reqBody.type == RequestType.Boy) {
                val fromIndex = position

                val responseList =
                    searchBoys(from = fromIndex, loadSize)

                if(responseList.isEmpty() ) {
                    //请求结束的时， 可以返回一个特殊的异常， 来标记这个已经加载完成了
                        // 然后重试的时候修改一下type， 重新请求一遍就可以加载girl数据
                    return LoadResult.Error(LoadMoreException())
                }else {

                    bodySize = fromIndex + responseList.size
                    LoadResult.Page(
                        data = responseList,
                        prevKey = if (position == Start_Index) null else position - loadSize,
                        nextKey = if (responseList.isEmpty()) position + loadSize else if (responseList.size < loadSize)
                            null else position + loadSize
                    )
                }
            } else {
                val fromIndex = position - bodySize + 1

                val responseList =
                    searchGirls(from = fromIndex , loadSize)

                LoadResult.Page(
                    data = responseList,
                    prevKey = if (position == bodySize) null else position - loadSize,
                    nextKey = if (responseList.isEmpty()) null else if (responseList.size < loadSize)
                        null else position + loadSize
                )
            }



        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    private suspend fun searchBoys(from: Int, size: Int): List<Boy> {

        //最多加载100条
        delay(2000)

        return if (from + size > 110) {

           return listOf()

        }else {


            return (from..(from + size)).map {

                Boy("body: $it")
            }
        }

    }

    private suspend fun searchGirls(from: Int, size: Int): List<Girl> {
        delay(2000)

        //最多加载100条
        return if (from + size > 110) {

            return listOf()


        }else {


            return (from..(from + size)).map {

                Girl("girl: $it")
            }
        }

    }


}

class LoadMoreException: Exception()