package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.InMemoryPostRepository
import ru.netology.nmedia.repository.PostRepository

class PostViewModel: ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()
    val data = repository.get()
    fun like(){
        repository.like()
    }
    fun share(){
        repository.share()
    }
    fun convert(num: Int): String {
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
}