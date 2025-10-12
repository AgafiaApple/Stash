package com.example.sharingapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AllItemsFragment()
            1 -> AvailableItemsFragment()
            2 -> BorrowedItemsFragment()
            else -> throw IllegalArgumentException("Invalid position") // Or return a default fragment
        }
    }

    override fun getCount(): Int {
        return 3 // Three pages
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "All"
            1 -> "Available"
            2 -> "Borrowed"
            else -> null
        }
    }
}
