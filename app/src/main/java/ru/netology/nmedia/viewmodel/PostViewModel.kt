package ru.netology.nmedia.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.post.Post
import ru.netology.nmedia.repository.InMemoryPostRepository
import ru.netology.nmedia.repository.PostRepository

val empty = Post(
    0,
    "Me",
    "",
    "now",
    false,
    0,
    0
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()
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