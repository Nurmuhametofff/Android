package ru.netology.nmedia.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.post.Post
import ru.netology.nmedia.repository.InMemoryPostRepository
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryFileImpl

val empty = Post(
    0,
    "Me",
    "",
    "now",
    false,
    null,
    0
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val data = repository.getAll()

    fun likeById(id: Long) {
        repository.likeById(id)
    }

    fun shareById(id: Long) {
        repository.shareById(id)
    }

    fun removeById(id: Long) {
        repository.removeById(id)
    }
    val edited = MutableLiveData(empty)

    fun edit(post: Post){
        edited.value = post
    }


    fun save(){
        edited.value?.let {
            repository.save(it)
            edited.value = empty
        }
    }

    fun editContent(content: String){
        edited.value?.let {
            val trimmed = content.trim()
            if(trimmed == it.content){
                return
            }
            edited.value = it.copy(content = trimmed)
        }
    }
}