package ru.netology.nmedia.dto.post

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val isLike: Boolean,
    var countLikes: Long = 0,
    val countShare: Long = 0
)