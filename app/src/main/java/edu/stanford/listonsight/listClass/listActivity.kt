package edu.stanford.listonsight.listClass

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.stanford.listonsight.R
import edu.stanford.listonsight.data.DataSource
import edu.stanford.listonsight.data.ListItem

const val LIST_CLASS_ID = "LIST_ID"
class ListActivity: AppCompatActivity() {
    private val newListActivityRequestCode = 1

    /*Opens list activity when recycler view is clicked*/
    private fun adapterOnClick(listItem: ListItem) {
        val intent = Intent(this, ListActivity()::class.java)
        intent.putExtra(LIST_CLASS_ID, listItem.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        /*Instantiates ListClassAdapter*/
        val listClassAdapter = ListClassAdapter {listItem -> adapterOnClick(listItem)}

        val recyclerView: RecyclerView = findViewById(R.id.listApp_recyclerView)
        recyclerView.adapter = listClassAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val listClassViewModel = ViewModelProvider(
            this, ListClassViewModelFactory(this)).get(ListClassViewModel::class.java)

        listClassViewModel.listLiveData.observe(this, {
            it?.let{
                listClassAdapter.submitList(it as MutableList<ListItem>)
            }
        })
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        /*Inserts ListItem into ListClassViewModel*/
//        if (requestCode == newListActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            intentData?.let { data ->
//                val listItemTitle = data.getStringExtra(LIST)
//            }
//        }
//    }
}