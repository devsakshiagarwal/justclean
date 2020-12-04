package com.agarwal.justclean.ui.home.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agarwal.justclean.R
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.ui.home.HomeViewModel
import com.agarwal.justclean.ui.home.post.PostAdapter.OnPostClickListener
import com.agarwal.justclean.utils.InternetConnectionUtil
import com.agarwal.justclean.utils.Response
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_post.constraintLayout
import kotlinx.android.synthetic.main.fragment_post.rv_posts
import kotlinx.android.synthetic.main.layout_progress_bar.progress_bar

class PostFragment : Fragment(), OnPostClickListener {
  private lateinit var homeViewModel: HomeViewModel
  private var isFavorite = false

  companion object {
    @JvmStatic fun newInstance(isFavorite: Boolean) = PostFragment().apply {
      arguments = Bundle().apply {
        putBoolean("is_fav", isFavorite)
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_post, container, false)
  }

  override fun onViewCreated(view: View,
    savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    isFavorite = arguments?.getBoolean("is_fav", false)!!
    homeViewModel =
      ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    observeLiveData()
  }

  override fun onPostClick(post: Post) {
    findNavController().navigate(R.id.commentFragment,
      bundleOf("post" to post))
  }

  private fun observeLiveData() {
    if (isFavorite) {
      homeViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
        setData(it)
      })
    } else {
      homeViewModel.postList.observe(viewLifecycleOwner, Observer {
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
    if (!InternetConnectionUtil.isConnectedToInternet(
        requireActivity() as AppCompatActivity)) {
      Snackbar.make(constraintLayout, getString(R.string.err_no_internet),
        Snackbar.LENGTH_SHORT)
        .show()
    } else {
      Snackbar.make(constraintLayout, errorMessage, Snackbar.LENGTH_SHORT)
        .show()
    }
  }
}