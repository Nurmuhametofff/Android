package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener{
            if(binding.content.text.isNullOrBlank()){
                Toast.makeText(this, getString(R.string.empty_post_error), Toast.LENGTH_SHORT).show()
                setResult(RESULT_CANCELED)
            }else{
                val result = Intent().putExtra(Intent.EXTRA_TEXT, binding.content.text.toString())
                setResult(RESULT_OK, result)
            }

            finish()
        }

    }
}