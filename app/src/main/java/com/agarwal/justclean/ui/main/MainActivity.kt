package com.agarwal.justclean.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agarwal.justclean.R.layout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    initViews()
  }

  private fun initViews() {
    val sectionsPagerAdapter =
      MainPagerAdapter(this, supportFragmentManager)
    view_pager.adapter = sectionsPagerAdapter
    tabs_main.setupWithViewPager(view_pager)
  }
}