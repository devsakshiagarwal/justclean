package com.agarwal.justclean.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.agarwal.justclean.R
import com.agarwal.justclean.R.layout
import com.agarwal.justclean.model.local.SharedPref
import com.agarwal.justclean.model.work_manager.WorkManagerScheduler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_toolbar.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
    initViews()
    initiateWorkManager()
  }

  private fun initViews() {
    val navHostFragment: NavHostFragment =
      supportFragmentManager.findFragmentById(
        R.id.nav_host_fragment) as NavHostFragment
    val navController: NavController = navHostFragment.navController
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    tool_bar.setupWithNavController(navController, appBarConfiguration)
  }

  private fun initiateWorkManager() {
    if (SharedPref(this).isUpdate()) {
      WorkManagerScheduler.updateWorkOnInternetConnected(this)
    }
  }
}