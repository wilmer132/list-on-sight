package edu.stanford.listonsight.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialListInstance = userListSample(resources)
    private val listLiveData = MutableLiveData(initialListInstance)

    /*Adds ListItems to liveData and posts value*/
    fun addListItem(listItem: ListItem) {
        val currentList = listLiveData.value
        if (currentList == null) {
            listLiveData.postValue(listOf(listItem))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, listItem)
            listLiveData.postValue(updatedList)
        }
    }

    /*Removes listItem from livedata and posts values*/
    fun removeListItem(listItem: ListItem) {
        val currentList = listLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(listItem)
            listLiveData.postValue(updatedList)
        }
    }

    /*Returns listItem given an ID*/
    fun getListItemForId(id: Long): ListItem? {
        listLiveData.value?.let { listItems ->
            return listItems.firstOrNull{it.id == id}
        }
        return null
    }

    fun getListItems(): LiveData<List<ListItem>> {
        return listLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}