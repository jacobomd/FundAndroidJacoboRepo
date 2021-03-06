package io.keepcoding.eh_ho.login

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import io.keepcoding.eh_ho.R
import io.keepcoding.eh_ho.data.RequestError
import io.keepcoding.eh_ho.data.SignModel
import io.keepcoding.eh_ho.data.SignUpModel
import io.keepcoding.eh_ho.data.UserRepo
import io.keepcoding.eh_ho.topics.TopicsActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    SignInFragment.SignInInteractionListener,
    SignUpFragment.SignUpInteractionListener
{

    val signInFragment: SignInFragment =
        SignInFragment()
    val signUpFragment: SignUpFragment =
        SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        if (savedInstanceState == null) {
            checkSession()
        }
    }

    private fun checkSession() {
        if (UserRepo.isLogged(this))
            launchTopicsActivity()
        else
            onGoToSignIn()
    }


    // Interface del fragment SignInInteractionListener
    override fun onGoToSignUp() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, signUpFragment)
            .commit()
    }

    override fun onSignIn(signModel: SignModel) {
        enableLoading(true)
        UserRepo.signIn(this,signModel,
            {
                enableLoading(false)
                launchTopicsActivity()
            },
            {
                enableLoading(false)
               handleRequestError(it)
            })

    }

    // Interface del fragment SignUpInteractionListener
    override fun onGoToSignIn() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, signInFragment)
            .commit()
    }

    override fun onSignUp(signUpModel: SignUpModel) {
        enableLoading(true)
        UserRepo.signUp(
            this,
            signUpModel,
            {
                enableLoading(false)
                launchTopicsActivity()
            },
            {
                enableLoading(false)
                handleRequestError(it)
            }
        )
    }

    private fun handleRequestError(requestError: RequestError) {
        val message = if (requestError.messageId != null)
            getString(requestError.messageId)
        else if (requestError.message != null)
            requestError.message
        else
            getString(R.string.error_request_default)

        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show()

    }


    private fun enableLoading(enable: Boolean) {
        if (enable) {
            fragmentContainer.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            fragmentContainer.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }


    private fun launchTopicsActivity() {
        val intent = Intent(this, TopicsActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Metodos para simular progressBar de varias maneras con tareas asincronas ....

/*    private fun simulateLoading() {
        val runnable = Runnable {
            Thread.sleep(3 * 1000)
            viewLoading.post {
                launchTopicsActivity()
            }
        }
        Thread(runnable).start()
    }

    private fun simulateLoadingTask() {
        val task = object : AsyncTask<Void, Void, Boolean> () {
            override fun doInBackground(vararg params: Void?): Boolean {
                Thread.sleep(3 * 1000)
                return true
            }

            override fun onPostExecute(result: Boolean?) {
                super.onPostExecute(result)
                launchTopicsActivity()
            }
        }

        task.execute()
    }*/
}
