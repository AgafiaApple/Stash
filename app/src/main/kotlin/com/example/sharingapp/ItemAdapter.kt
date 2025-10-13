package com.example.sharingapp

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Item Adapter is responsible for what information is displayed in ListView entries.
 */
class ItemAdapter(
    context: Context,
    items: ArrayList<Item>,
    private val fragment: Fragment
) : ArrayAdapter<Item>(context, 0, items) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val context: Context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // getItem(position) gets the "item" at "position" in the "items" ArrayList
        // (where "items" is a parameter in the ItemAdapter creator as seen above ^^)
        // Note: getItem() is not a user-defined method in the Item/ItemList class!
        // The "Item" in the method name is a coincidence...
        val item = getItem(position) ?: return convertView ?: inflater.inflate(R.layout.itemlist_item, parent, false)

        val title = "Title: ${item.title}" // Using string templates
        val description = "Description: ${item.description}"
        val thumbnail = item.image
        val status = "Status: ${item.status}"

        // Check if an existing view is being reused, otherwise inflate the view.
        val view = convertView ?: inflater.inflate(R.layout.itemlist_item, parent, false)

        val titleTv: TextView = view.findViewById(R.id.title_tv)
        val statusTv: TextView = view.findViewById(R.id.status_tv)
        val descriptionTv: TextView = view.findViewById(R.id.description_tv)
        val photo: ImageView = view.findViewById(R.id.image_view)

        thumbnail?.let { // Null-safe operation
            photo.setImageBitmap(it)
        } ?: run {
            photo.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        titleTv.text = title
        descriptionTv.text = description

        // AllItemFragments: itemlist_item shows title, description and status
        if (fragment is AllItemsFragment) {
            statusTv.text = status
        }

        // BorrowedItemsFragment/AvailableItemsFragment: itemlist_item shows title and description only
        if (fragment is BorrowedItemsFragment || fragment is AvailableItemsFragment) {
            statusTv.visibility = View.GONE
        }

        return view
    }
}
