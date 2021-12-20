package edu.stanford.listonsight.listClass

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.stanford.listonsight.data.DataSource
import edu.stanford.listonsight.data.ListItem
import java.lang.IllegalArgumentException
import kotlin.random.Random

class ListClassViewModel(val dataSource: DataSource) : ViewModel() {
    val listLiveData = dataSource.getListItems()

    /*If the name and description are present, create new ListItem and add to datasource*/
    fun insertListItem(listItemTitle: String?, listItemDescription: String?) {
        if (listItemTitle == null || listItemDescription == null) {
            return
        }

        val newListItem = ListItem(
                Random.nextLong(),
                listItemTitle,
                listItemDescription,
                false
        )

        dataSource.addListItem(newListItem)
    }
}

class ListClassViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListClassViewModel::class.java)) {
           //@Supress("UNCHECKED_CAST")
            return ListClassViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}