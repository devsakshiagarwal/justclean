package com.agarwal.justclean.ui.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agarwal.justclean.R
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.ui.main.MainViewModel
import com.agarwal.justclean.ui.main.comment.CommentFragment
import com.agarwal.justclean.ui.main.post.PostAdapter.OnPostClickListener
import com.agarwal.justclean.utils.Response
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_post.constraintLayout
import kotlinx.android.synthetic.main.fragment_post.rv_posts
import kotlinx.android.synthetic.main.layout_progress_bar.progress_bar

class PostFragment : Fragment(), OnPostClickListener {
  private lateinit var mainViewModel: MainViewModel

  companion object {
    @JvmStatic fun newInstance() = PostFragment()
  }

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_post, container, false)
  }

  override fun onViewCreated(view: View,
    savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mainViewModel =
      ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    initViews()
    observeLiveData()
  }

  override fun onPostClick(post: Post) {
    val ft: FragmentTransaction =
      activity!!.supportFragmentManager.beginTransaction()
    ft.replace(R.id.cl_main, CommentFragment.newInstance(post.id, post.body))
    ft.addToBackStack("main_fragment")
    ft.commit()
  }

  private fun initViews() {
  }

  private fun observeLiveData() {
    mainViewModel.postList.observe(requireActivity(), Observer {
      when (it.status) {
        Response.Status.SUCCESS -> {
          setData(it.data!!)
        }
        Response.Status.ERROR -> {
          showError(it.message!!)
        }
        Response.Status.LOADING -> progress_bar.visibility = View.VISIBLE
      }
    })
  }

  private fun setData(postList: List<Post>) {
    progress_bar.visibility = View.GONE
    val postAdapter = PostAdapter(this)
    postAdapter.setPosts(postList)
    rv_posts.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = postAdapter
    }
  }

  private fun showError(errorMessage: String) {
    progress_bar.visibility = View.GONE
    Snackbar.make(constraintLayout, errorMessage, Snackbar.LENGTH_SHORT)
      .show()
  }
}