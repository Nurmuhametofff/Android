package ru.netology.nmedia.dto.post

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val isLike: Boolean,
    val video: String? = null,
    val countLikes: Long = 0,
    val countShare: Long = 0
)