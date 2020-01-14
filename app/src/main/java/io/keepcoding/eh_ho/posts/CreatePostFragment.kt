package io.keepcoding.eh_ho.posts


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import io.keepcoding.eh_ho.R
import kotlinx.android.synthetic.main.fragment_create_post.*


class CreatePostFragment : Fragment() {

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
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_send_post, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)

        when (item?.itemId) {
            //R.id.menu_button_send_post ->
        }
    }


}
