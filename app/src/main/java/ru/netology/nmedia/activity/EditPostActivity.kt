package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityEditPostBinding
import ru.netology.nmedia.util.AndroidUtils

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.group.visibility = View.GONE
        binding.closeEditor.setOnClickListener {
            binding.group.visibility = View.GONE
            binding.editContent.clearFocus()
            AndroidUtils.hideKeyboard(binding.editContent)
            binding.editContent.setText("")

            finish()
        }


        val content = intent.getStringExtra(Intent.EXTRA_TEXT)

        binding.editContent.setText(content)
        binding.save.setOnClickListener {

            if (binding.editContent.text.isNullOrBlank()) {
                Toast.makeText(this, getString(R.string.empty_post_error), Toast.LENGTH_SHORT)
                    .show()
                setResult(RESULT_CANCELED)
            } else {
                val result =
                    Intent().putExtra(Intent.EXTRA_TEXT, binding.editContent.text.toString())
                setResult(RESULT_OK, result)
            }

            finish()
        }
    }
}