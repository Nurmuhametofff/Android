package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.counts.Counts
import ru.netology.nmedia.dto.post.Post
import kotlin.math.ln
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(binding.likes)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36"
        )
        val counts = Counts(
            countLikes = 1299,
            countShare = 1298,
            countVisibility = 7
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            countLikes.text = convert(counts.countLikes)
            countShare.text = convert(counts.countShare)
            visibility.text = convert(counts.countVisibility)

            likes.setOnClickListener {
                post.isLike = !post.isLike
                likes.setImageResource(
                    if (post.isLike) {
                        counts.countLikes++
                        countLikes.text = convert(counts.countLikes)
                        R.drawable.ic_baseline_favorite_24
                    } else {
                        counts.countLikes--
                        countLikes.text = convert(counts.countLikes)
                        R.drawable.ic_baseline_favorite_border_24
                    }
                )
            }
            share.setOnClickListener {
                counts.countShare++
                countShare.text = convert(counts.countShare)
            }
//            binding.root.setOnClickListener{
//                println("binding root")
//            }
//            binding.likes.setOnClickListener{
//                println("binding likes")
//            }
//            avatar.setOnClickListener {
//                println("avatar")
//            }
        }
    }
}
//Нашел на СтекОверФлоу(выглядит симпотично, но ничего не понятно)

//fun getFormatedNumber(count: Long): String {
//    if (count < 1000) return "" + count
//    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
//    return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
//}

//Это мой костыль)
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
