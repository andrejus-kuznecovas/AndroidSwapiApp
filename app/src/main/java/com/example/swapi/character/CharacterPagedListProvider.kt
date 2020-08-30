package com.example.swapi.character

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.swapi.model.Character
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CharacterPagedListProvider(private val factory: DataSource.Factory<Int, Character?>) :
    PagedListProvider<Character?> {

    override fun provide(): LiveData<PagedList<Character?>> {
        return LivePagedListBuilder(
            factory, PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .setInitialLoadSizeHint(10)
                .build()
        ).setFetchExecutor(createFetchExecutor())
            .build()
    }

    private fun createFetchExecutor(): ExecutorService {
        val nThreads = Runtime.getRuntime().availableProcessors() + 1
        return Executors.newFixedThreadPool(nThreads)
    }
}

interface PagedListProvider<Value> {

    fun provide(): LiveData<PagedList<Value>>
}