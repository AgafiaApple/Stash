package com.example.sharingapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import 	androidx.viewpager2.widget.ViewPager2

/**
 * A {@link FragmentStateAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

// TODO: uncomment this when you implement ItemsActivity and Item classes

//class SectionsPageAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa ) {
//
//    // get a list of all the different sections that help organize
//    // the user's items
//    private val pages = listOf(
//        AllItemsFragment(),
//        AvailableItemsFragment(),
//        BorrowedItemsFragment()
//    )
//
//    // a count of the number of pages
//    override fun getItemCount() : Int = pages.size
//
//    override fun createFragment(position: Int): Fragment {
//        return when(position) {
//            0 -> AllItemsFragment
//            1 -> AvailableItemsFragment()
//            2 -> BorrowedItemsFragment()
//            else -> throw IllegalArgumentException("Invalid position")
//        }
//
//
//    }
//}

