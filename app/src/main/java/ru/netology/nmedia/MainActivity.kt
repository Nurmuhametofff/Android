package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.post.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        binding.group.visibility = View.GONE
        val adapter = PostAdapter(
            object : OnInteractionListener{
                override fun onEdit(post: Post) {
                   viewModel.edit(post)
                    binding.group.visibility = View.VISIBLE
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }
            }
            )

        viewModel.edited.observe(this){ edited ->
            if (edited.id == 0L){
                return@observe
            }
            binding.content.setText(edited.content)
            binding.content.requestFocus()

        }

        binding.closeEditor.setOnClickListener {
            binding.group.visibility = View.GONE
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(binding.content)
            binding.content.setText("")
        }

        binding.save.setOnClickListener{
            if(binding.content.text.isNullOrBlank()){
                Toast.makeText(it.context, getString(R.string.empty_post_error),
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val text = binding.content.text?.toString().orEmpty()


            viewModel.editContent(text)
            viewModel.save()

            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(binding.content)
            binding.content.setText("")
            binding.group.visibility = View.GONE
        }

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }

}
