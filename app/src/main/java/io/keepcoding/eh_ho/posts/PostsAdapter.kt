package io.keepcoding.eh_ho.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.data.Post
import io.keepcoding.eh_ho.data.Topic
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostHolder>() {

    private val posts = mutableListOf<Post>()
    private val listener : ((View) -> Unit) = {
        val post = it.tag as Topic
        //topicClickListener?.invoke(topic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post = posts[position]
        holder.post= post
        holder.itemView.setOnClickListener (listener)
    }

    fun setPosts (posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    inner  class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var post : Post? = null
            set(value) {
                field = value

                with(itemView) {
                    tag = field

                    field?.let {
                        textViewTitle.text = field?.username
                        textViewContent.text = field?.cooked
                        textViewDate.text = field?.createdAt.toString()
                        /*labelTitle.text = field?.title
                        labelPosts.text = field?.posts.toString()
                        labelViews.text = field?.views.toString()
                        setTimeOffset(it.getTimeOffset())*/
                    }

                }


            }

        private fun setTimeOffset(timeOffset: Topic.TimeOffset) {
            val quantityString = when (timeOffset.unit) {
                Calendar.YEAR -> R.plurals.years
                Calendar.MONTH -> R.plurals.months
                Calendar.DAY_OF_MONTH -> R.plurals.days
                Calendar.HOUR -> R.plurals.hours
                else -> R.plurals.minutes
            }

            itemView.textViewDate.text =
                if (timeOffset.amount != 0)
                    itemView.context.resources.getQuantityString(quantityString, timeOffset.amount, timeOffset.amount)
                else
                    itemView.context.resources.getString(R.string.minutes_zero)

        }


    }
}