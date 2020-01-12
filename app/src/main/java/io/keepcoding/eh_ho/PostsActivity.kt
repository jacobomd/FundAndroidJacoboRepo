package io.keepcoding.eh_ho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.keepcoding.eh_ho.data.TopicsRepo
import kotlinx.android.synthetic.main.activity_posts.*
import java.lang.IllegalArgumentException

const val EXTRA_TOPIC_ID = "topic_id"


class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val topicId = intent.getStringExtra(EXTRA_TOPIC_ID)

        if (topicId !=null && topicId.isNotEmpty()) {


        } else {
            throw IllegalArgumentException("You should provide an id for the topic")
        }



    }
}
