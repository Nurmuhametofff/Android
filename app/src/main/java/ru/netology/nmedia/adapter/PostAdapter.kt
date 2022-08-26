package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.post.Post

interface OnInteractionListener{
    fun onLike(post: Post) {}
    fun onShare(post: Post){}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onVideo(post: Post) {}

}

class PostAdapter(
    private val listener: OnInteractionListener,
    ): ListAdapter<Post, PostViewHolder>(PostDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(
            binding = binding,
            listener = listener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}
class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: OnInteractionListener): RecyclerView.ViewHolder(binding.root){
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            share.text = convert(post.countShare)
            likes.text = convert(post.countLikes)


            likes.isChecked = post.isLike
            likes.setOnClickListener{
                listener.onLike(post)
            }
            share.setOnClickListener{
                listener.onShare(post)
            }
            if(post.video==null) binding.playVideoGroup.visibility = View.GONE else binding.playVideoGroup.visibility = View.VISIBLE

            play.setOnClickListener { listener.onVideo(post) }
            backgroundVideo.setOnClickListener { listener.onVideo(post) }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { menuItem ->
                        when(menuItem.itemId){
                            R.id.remove -> {
                                listener.onRemove(post)
                                return@setOnMenuItemClickListener true
                            }
                            R.id.edit -> {
                                listener.onEdit(post)
                                return@setOnMenuItemClickListener true
                            }
                        }

                        false
                    }

                    show()
                }
            }
        }
    }

}
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
    override fun getChangePayload(oldItem: Post, newItem: Post): Any = Unit
}
fun convert(num: Long): String {
    return if (num < 1000) {
        num.toString()
    } else if (num < 10000) {
        val thousand = num / 1000
        val hundred = ((num % 1000) / 100)
        String.format("$thousand.$hundred k")
    } else if (num < 1000000) {
        val tenThousand = num / 1000
        String.format("$tenThousand k")
    } else {
        val million = num / 1000000
        String.format("$million M")
    }
}