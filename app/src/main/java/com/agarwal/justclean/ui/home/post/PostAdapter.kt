package com.agarwal.justclean.ui.home.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.agarwal.justclean.R
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.ui.home.post.PostAdapter.PostViewHolder

class PostAdapter(private val onPostClickListener: OnPostClickListener) :
  RecyclerView.Adapter<PostViewHolder>() {
  private lateinit var postList: List<Post>

  fun setPosts(postList: List<Post>) {
    this.postList = postList
  }

  override fun onCreateViewHolder(parent: ViewGroup,
    viewType: Int): PostViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return PostViewHolder(inflater, parent)
  }

  override fun onBindViewHolder(holder: PostViewHolder,
    position: Int) {
    holder.bind(postList[position], onPostClickListener)
  }

  override fun getItemCount(): Int = postList.size

  class PostViewHolder(inflater: LayoutInflater,
    parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_post, parent, false)) {
    private var tvTitle: AppCompatTextView =
      itemView.findViewById(R.id.tv_title)
    private var tvDescription: AppCompatTextView =
      itemView.findViewById(R.id.tv_body)

    fun bind(post: Post,
      onPostClickListener: OnPostClickListener) {
      tvTitle.text = post.title
      tvDescription.text = post.body
      itemView.setOnClickListener {
        onPostClickListener.onPostClick(post)
      }
    }
  }

  interface OnPostClickListener {
    fun onPostClick(post: Post)
  }
}