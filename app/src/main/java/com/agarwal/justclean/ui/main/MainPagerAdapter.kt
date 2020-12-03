package com.agarwal.justclean.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.agarwal.justclean.R
import com.agarwal.justclean.ui.main.post.PostFragment

private val TAB_TITLES = arrayOf(R.string.tab_text_1, R.string.tab_text_2)

class MainPagerAdapter(private val context: Context,
  fm: FragmentManager) : FragmentPagerAdapter(fm) {

  override fun getItem(position: Int): Fragment {
    return PostFragment.newInstance()
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return context.resources.getString(TAB_TITLES[position])
  }

  override fun getCount(): Int {
    return 2
  }
}