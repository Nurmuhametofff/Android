package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.counts.Counts
import ru.netology.nmedia.dto.post.Post
import ru.netology.nmedia.repository.InMemoryPostRepository
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = InMemoryPostRepository()

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->

            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content

                val likeImage = if (post.isLike) {
                    R.drawable.ic_baseline_favorite_24
                } else {
                    R.drawable.ic_baseline_favorite_border_24
                }
                likes.setImageResource(likeImage)

                likes.setOnClickListener {
                    viewModel.like()
                }
                countLikes.text = repository.convert(post.countLikes)
                share.setOnClickListener {
                    viewModel.share()
                }
                countShare.text = repository.convert(post.countShare)
            }
        }
    }

}
