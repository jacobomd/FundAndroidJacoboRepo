package io.keepcoding.eh_ho.topics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.keepcoding.eh_ho.*
import io.keepcoding.eh_ho.data.Topic
import io.keepcoding.eh_ho.data.TopicsRepo
import io.keepcoding.eh_ho.data.UserRepo
import io.keepcoding.eh_ho.login.LoginActivity
import kotlinx.android.synthetic.main.activity_topics.*

const val TRANSACTION_CREATE_TOPIC = "create_topic"


class TopicsActivity : AppCompatActivity(), TopicsFragment.TopicsInteractionListener, CreateTopicFragment.CreateTopicInterationListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topics)
       // Log.d(TopicsActivity::class.java.simpleName, TopicsRepo.topics.toString())

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, TopicsFragment())
                .commit()
        }

    }

   private fun goToPosts (topic: Topic) {
        val intent = Intent(this, PostsActivity::class.java)

        intent.putExtra(EXTRA_TOPIC_ID, topic.id)

        startActivity(intent)
    }

    override fun onTopicSelected(topic: Topic) {
        goToPosts(topic)
    }

    override fun onGoToCreateTopic() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CreateTopicFragment())
            .addToBackStack(TRANSACTION_CREATE_TOPIC)
            .commit()
    }

    override fun onTopicCreated() {
        supportFragmentManager.popBackStack()
    }

    override fun onLogOut() {
        UserRepo.logOut(this)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}