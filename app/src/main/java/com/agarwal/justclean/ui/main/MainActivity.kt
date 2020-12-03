package com.agarwal.justclean.ui.main

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.agarwal.justclean.R.id
import com.agarwal.justclean.R.layout
import com.agarwal.justclean.arch.network.Status.ERROR
import com.agarwal.justclean.arch.network.Status.LOADING
import com.agarwal.justclean.arch.network.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private lateinit var mainViewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    initViews()
    observeLiveData()
  }

  private fun initViews() {
    mainViewModel.getPosts()
    val sectionsPagerAdapter =
      SectionsPagerAdapter(this, supportFragmentManager)
    val viewPager: ViewPager = findViewById(id.view_pager)
    viewPager.adapter = sectionsPagerAdapter
    val tabs: TabLayout = findViewById(id.tabs)
    tabs.setupWithViewPager(viewPager)
    val fab: FloatingActionButton = findViewById(id.fab)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show()
    }
  }

  private fun observeLiveData() {
    mainViewModel.postsLiveData.observe(this, Observer {
      when (it.status) {
        SUCCESS -> {
          it.data.let { postList ->
          }
        }
        LOADING -> {
        }
        ERROR -> {
        }
      }
    })
  }
}