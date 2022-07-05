package ru.netology.nmedia.dto.post

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var isLike: Boolean = false
)