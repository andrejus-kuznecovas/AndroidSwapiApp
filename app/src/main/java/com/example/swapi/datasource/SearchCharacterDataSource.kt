package com.example.swapi.datasource

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.swapi.network.Character
import com.example.swapi.usecase.GetSearchedPeopleUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val FIRST_PAGE = 1

class SearchCharacterDataSource(
    private val searchCriteria: String?,
    private val getSearchedPeopleUseCase: GetSearchedPeopleUseCase = GetSearchedPeopleUseCase.create()
) :
    PageKeyedDataSource<Int, Character>() {
    private var dataSourceJob = Job()

    private val coroutineScope = CoroutineScope(dataSourceJob + Dispatchers.Main)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        coroutineScope.launch {
            try {
                Log.i("SearchCharacterDataSour", "seachCriteria: $searchCriteria")
                val searchResults = getSearchedPeopleUseCase.execute(searchCriteria, FIRST_PAGE)
                Log.i("SearchCharacterDataSour", "searchResults.size: ${searchResults.size}")
                callback.onResult(
                    getSearchedPeopleUseCase.execute(searchCriteria, FIRST_PAGE),
                    null,
                    FIRST_PAGE.inc()
                )
            } catch (e: Exception) {
                Log.e("SearchCharacterDataSour", "loadInitial has failed")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {

        coroutineScope.launch {
            try {
                callback.onResult(
                    getSearchedPeopleUseCase.execute(searchCriteria, params.key),
                    params.key.inc()
                )

            } catch (e: Exception) {
                Log.e("SearchCharacterDataSour", "loadBefore has failed")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        coroutineScope.launch {
            try {
                callback.onResult(
                    getSearchedPeopleUseCase.execute(searchCriteria, params.key),
                    params.key.inc()
                )
            } catch (e: Exception) {
                Log.e("SearchCharacterDataSour", "loadAfter has failed")
            }
        }
    }

    companion object {
        fun factory(searchCriteria: String?) = object : DataSource.Factory<Int, Character?>() {
            override fun create() = SearchCharacterDataSource(searchCriteria)
        }
    }

    override fun invalidate() {
        super.invalidate()
        dataSourceJob.cancel()
    }


}