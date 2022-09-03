package `in`.android.mads_calculator.ui.activities

import `in`.android.mads_calculator.R
import `in`.android.mads_calculator.databinding.LayoutAuthenticationBinding
import `in`.android.mads_calculator.utils.snack
import `in`.android.mads_calculator.utils.toggleVisibility
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class AuthenticationActivity : AppCompatActivity(), View.OnClickListener,
    OnCompleteListener<AuthResult> {

    private lateinit var binding: LayoutAuthenticationBinding
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = LayoutAuthenticationBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        checkLoginUser()
        setContentView(binding.root)
        setupOnClickListeners()


    }

    private fun checkLoginUser() {
        auth.currentUser?.let {
            loadCalculator()
        }
    }


    private fun setupOnClickListeners() {
        binding.apply {
            buttonLogin.setOnClickListener(this@AuthenticationActivity)
            btnSkip.setOnClickListener(this@AuthenticationActivity)
        }

    }

    override fun onClick(v: View?) {
        binding.apply {
            when (v) {
                buttonLogin -> {
                    if (isValidInput()) {
                        toggleLoader()
                        val email = edtEmail.editText!!.text.toString()
                        val pass = editPassword.editText!!.text.toString()
                        auth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(this@AuthenticationActivity)
                    }

                }

                btnSkip -> {
                    loadCalculator()
                }
            }
        }

    }

    private fun isValidInput(): Boolean {
        if (binding.edtEmail.editText!!.text.toString().trim().isEmpty()) {
            binding.edtEmail.error = getString(R.string.error_empty_email)
            binding.edtEmail.requestFocus()
            return false

        } else {
            val emailId: String = binding.edtEmail.editText!!.text.toString()
            val isValid = Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
            if (!isValid) {
                binding.edtEmail.error = getString(R.string.error_invalied_email)
                binding.edtEmail.requestFocus()
                return false
            } else {
                binding.edtEmail.isErrorEnabled = false
            }
        }
        if (binding.editPassword.editText!!.text.toString().trim().isEmpty()) {
            binding.editPassword.error = getString(R.string.error_empty_password)
            binding.editPassword.requestFocus()
            return false

        } else {
            if (binding.editPassword.editText!!.text.toString().length < 8) {
                binding.editPassword.error = getString(R.string.error_invalied_password)
                binding.editPassword.requestFocus()
                return false
            } else {
                binding.editPassword.isErrorEnabled = false
            }

        }
        return true
    }

    override fun onComplete(loginTask: Task<AuthResult>) {
        toggleLoader(false)
        if (loginTask.isSuccessful) {
            loadCalculator()
        } else {
            binding.buttonLogin.snack(
                loginTask.exception?.localizedMessage ?: "Invalid Credentials. Please try again"
            )
        }
    }

    private fun toggleLoader(show: Boolean = true) {
        binding.apply {
            main.toggleVisibility(!show)
            loader.toggleVisibility(show)
        }

    }

    fun loadCalculator() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}





