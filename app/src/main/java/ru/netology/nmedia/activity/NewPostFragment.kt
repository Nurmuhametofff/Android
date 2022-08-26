package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

//import ru.netology.nmedia.databinding.ActivityMainBinding
//import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentNewPostBinding.inflate(inflater, container, false)



        arguments?.textArg?.let(binding.content::setText)

        binding.content.requestFocus()

        binding.save.setOnClickListener {
            viewModel.editContent(binding.content.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }


        return binding.root
    }

}


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityNewPostBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.save.setOnClickListener{
//            if(binding.content.text.isNullOrBlank()){
//                Toast.makeText(this, getString(R.string.empty_post_error), Toast.LENGTH_SHORT).show()
//                setResult(RESULT_CANCELED)
//            }else{
//                val result = Intent().putExtra(Intent.EXTRA_TEXT, binding.content.text.toString())
//                setResult(RESULT_OK, result)
//            }
//
//            finish()
//        }
//        val content = intent.getStringExtra(Intent.EXTRA_TEXT)
//        binding.content.setText(content)
//
//        intent?.let {
//            if (it.action != Intent.ACTION_SEND){
//                return@let
//            }
//            val text = it.getStringExtra(Intent.EXTRA_TEXT)
//            if (text.isNullOrBlank()){
//                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
//}