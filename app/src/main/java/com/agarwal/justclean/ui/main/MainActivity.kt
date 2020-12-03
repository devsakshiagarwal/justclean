package com.agarwal.justclean.ui.main

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.agarwal.justclean.R.id
import com.agarwal.justclean.R.layout

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(
      layout.activity_main)
    val sectionsPagerAdapter =
      SectionsPagerAdapter(this, supportFragmentManager)
    val viewPager: ViewPager = findViewById(
      id.view_pager)
    viewPager.adapter = sectionsPagerAdapter
    val tabs: TabLayout = findViewById(
      id.tabs)
    tabs.setupWithViewPager(viewPager)
    val fab: FloatingActionButton = findViewById(
      id.fab)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show()
    }
  }
}