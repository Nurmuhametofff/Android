package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.post.Post

class InMemoryPostRepository : PostRepository {

    var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        isLike = false,
        countLikes = 1299,
        countShare = 8998
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(
            isLike = !post.isLike,
            countLikes = if (post.isLike) post.countLikes - 1 else post.countLikes + 1
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(countShare = post.countShare + 1)
        data.value = post
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