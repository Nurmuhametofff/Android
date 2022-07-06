package ru.netology.nmedia.dto.post

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val isLike: Boolean,
    val countLikes: Int = 0,
    val countShare: Int = 0
)