package io.keepcoding.eh_ho.posts


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.data.CreatePostModel
import io.keepcoding.eh_ho.data.PostsRepo
import io.keepcoding.eh_ho.data.RequestError
import kotlinx.android.synthetic.main.fragment_create_post.*
import kotlinx.android.synthetic.main.fragment_create_post.parentLayout



class CreatePostFragment : Fragment() {

    var topicId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topicTitle = arguments?.getString(EXTRA_TOPIC_TITLE)
        labelTitle.text = topicTitle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topicId= arguments?.getString(EXTRA_TOPIC_ID)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_send_post, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_button_send_post -> postPost()
        }
        return super.onOptionsItemSelected(item)

    }


    private fun postPost() {
        val model = CreatePostModel(
            editPost.text.toString(),
            topicId.toString().toInt()
        )
        context?.let {
            PostsRepo.createPost(
                it,
                model,
                {
                    Toast.makeText(context, "creacion exitosaaaaaa", Toast.LENGTH_LONG).show()
                },
                {
                    handleError(it)
                }
            )
        }
    }

    private fun handleError(requestError: RequestError) {
        val message = if (requestError.messageId != null)
            getString(requestError.messageId)
        else if (requestError.message != null)
            requestError.message
        else
            getString(R.string.error_request_default)

        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
