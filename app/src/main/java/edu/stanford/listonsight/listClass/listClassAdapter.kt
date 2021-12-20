package edu.stanford.listonsight.listClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.stanford.listonsight.R
import edu.stanford.listonsight.data.ListItem

class ListClassAdapter(private val onClick: (ListItem) -> Unit) :
    ListAdapter<ListItem, ListClassAdapter.ListItemViewHolder>(ListDiffCallback) {

    /*ViewHolder for List, takes in the inflated view and onClick behavior*/
    class ListItemViewHolder(itemView: View, val onClick: (ListItem) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val listItemTitleTextView: TextView = itemView.findViewById(R.id.listItemTitleInput)
        private val listItemDetailsTextView: TextView = itemView.findViewById(R.id.listItemDetailsInput)
        private val listItemCheckBox: CheckBox = itemView.findViewById(R.id.listItemCheckBox)
        private var currentListItem: ListItem? = null

        init {
            itemView.setOnClickListener {
                currentListItem?.let {
                    onClick(it)
                }
            }
        }

        /*Bind List Title and Details*/
        fun bind(listItem: ListItem) {
            currentListItem = listItem

            listItemTitleTextView.text = listItem.title
            listItemDetailsTextView.text = listItem.description
            listItemCheckBox.isChecked = listItem.complete
        }
    }

    /*Creates and inflates view and return ListItemViewHolder*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_class_item, parent, false)
        return ListItemViewHolder(view, onClick)
    }

    /*Gets current list item and uses it to bind view*/
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.bind(listItem)
    }
}

object ListDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.id == newItem.id
    }

}