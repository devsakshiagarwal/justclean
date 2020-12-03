package com.agarwal.justclean.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.agarwal.justclean.R.layout
import com.agarwal.justclean.arch.network.Status.ERROR
import com.agarwal.justclean.arch.network.Status.LOADING
import com.agarwal.justclean.arch.network.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

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
    view_pager.adapter = sectionsPagerAdapter
    tabs_main.setupWithViewPager(view_pager)
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