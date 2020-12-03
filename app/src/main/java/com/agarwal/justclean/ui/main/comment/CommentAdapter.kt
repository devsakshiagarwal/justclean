package com.agarwal.justclean.ui.main.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.agarwal.justclean.R
import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.ui.main.comment.CommentAdapter.CommentViewHolder

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
  private lateinit var commentList: List<Comment>

  fun setComments(commentList: List<Comment>) {
    this.commentList = commentList
  }

  override fun onCreateViewHolder(parent: ViewGroup,
    viewType: Int): CommentViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return CommentViewHolder(inflater, parent)
  }

  override fun onBindViewHolder(holder: CommentViewHolder,
    position: Int) {
    holder.bind(commentList[position])
  }

  override fun getItemCount(): Int = commentList.size

  class CommentViewHolder(inflater: LayoutInflater,
    parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(R.layout.item_post, parent, false)) {
    private var tvTitle: AppCompatTextView =
      itemView.findViewById(R.id.tv_title)
    private var tvDescription: AppCompatTextView =
      itemView.findViewById(R.id.tv_body)

    fun bind(comment: Comment) {
      tvTitle.text = comment.name
      tvDescription.text = comment.body
    }
  }
}