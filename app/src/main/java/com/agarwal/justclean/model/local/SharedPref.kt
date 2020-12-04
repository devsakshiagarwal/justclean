package com.agarwal.justclean.model.local

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
  companion object {
    private const val PREF_NAME = "just_clean"
    private const val IS_UPDATE = "is_update"
  }

  private val sharedPref: SharedPreferences =
    context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

  fun isUpdate(): Boolean = sharedPref.getBoolean(IS_UPDATE, false)

  fun setUpdate(boolean: Boolean) {
    val editor = sharedPref.edit()
    editor.putBoolean(IS_UPDATE, boolean)
    editor.apply()
    editor.commit()
  }
}