package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
//import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.dto.post.Post
import ru.netology.nmedia.viewmodel.PostViewModel


class FeedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        binding.create.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }


        val adapter = PostAdapter(
            object : OnInteractionListener {
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }

                override fun onShare(post: Post) {
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plain"
                    }

                    val shareIntent =
                        Intent.createChooser(intent, getString(R.string.chooser_share_post))
                    startActivity(shareIntent)
                    viewModel.shareById(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onVideo(post: Post) {
                    val intentVideo = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    startActivity(intentVideo)
                }

            }
        )
        binding.list.adapter = adapter
        viewModel.edited.observe(viewLifecycleOwner) { edited ->
            if (edited.id == 0L) {
                return@observe
            }

        }
        return binding.root
    }
}

//        binding.closeEditor.setOnClickListener {
//            binding.group.visibility = View.GONE
//            binding.content.clearFocus()
//            AndroidUtils.hideKeyboard(binding.content)
//            binding.content.setText("")
//        }

//        binding.save.setOnClickListener{
//
//            val text = binding.content.text?.toString().orEmpty()
//
//
//
//
//            binding.content.clearFocus()
//            AndroidUtils.hideKeyboard(binding.content)
//            binding.content.setText("")
//            binding.group.visibility = View.GONE
//        }

//        binding.list.adapter = adapter
//        viewModel.data.observe(this) { posts ->
//            adapter.submitList(posts)
//        }
//
//        binding.create.setOnClickListener {
//            newPostLauncher.launch()
//        }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        }
//        setContentView(R.layout.fragment_feed)
//
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val viewModel: PostViewModel by viewModels()
//
//        val newPostLauncher = registerForActivityResult(NewPostActivityContract()){ text ->
//            text?:return@registerForActivityResult
//            viewModel.editContent(text)
//            viewModel.save()
//        }
//
//        val editPostLauncher = registerForActivityResult(EditPostActivityContract()){ text ->
//            text?:return@registerForActivityResult
//            viewModel.editContent(text)
//            viewModel.save()
//        }
//
//
//
////        binding.group.visibility = View.GONE
//        val adapter = PostAdapter(
//            object : OnInteractionListener{
//                override fun onEdit(post: Post) {
//                   viewModel.edit(post)
////                    binding.group.visibility = View.VISIBLE
//                    editPostLauncher.launch(post.content)
//                }
//
//                override fun onShare(post: Post) {
//                    val intent = Intent().apply {
//                        action = Intent.ACTION_SEND
//                        putExtra(Intent.EXTRA_TEXT, post.content)
//                        type = "text/plain"
//                    }
//
//                    val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
//                    startActivity(shareIntent)
//                    viewModel.shareById(post.id)
//                }
//
//                override fun onRemove(post: Post) {
//                    viewModel.removeById(post.id)
//                }
//
//                override fun onLike(post: Post) {
//                    viewModel.likeById(post.id)
//                }
//                override fun onVideo(post: Post) {
//                    val intentVideo = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
//                    startActivity(intentVideo)
//                }
//
//            }
//            )
//
//        viewModel.edited.observe(this){ edited ->
//            if (edited.id == 0L){
//                return@observe
//            }
////            binding.content.setText(edited.content)
////            binding.content.requestFocus()
//
//        }
//
////        binding.closeEditor.setOnClickListener {
////            binding.group.visibility = View.GONE
////            binding.content.clearFocus()
////            AndroidUtils.hideKeyboard(binding.content)
////            binding.content.setText("")
////        }
//
////        binding.save.setOnClickListener{
////
////            val text = binding.content.text?.toString().orEmpty()
////
////
////
////
////            binding.content.clearFocus()
////            AndroidUtils.hideKeyboard(binding.content)
////            binding.content.setText("")
////            binding.group.visibility = View.GONE
////        }
//
//        binding.list.adapter = adapter
//        viewModel.data.observe(this) { posts ->
//            adapter.submitList(posts)
//        }
//
//        binding.create.setOnClickListener{
//            newPostLauncher.launch()





