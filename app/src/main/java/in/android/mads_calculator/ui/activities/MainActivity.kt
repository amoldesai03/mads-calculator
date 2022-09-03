package `in`.android.mads_calculator.ui.activities

import `in`.android.mads_calculator.R
import `in`.android.mads_calculator.databinding.LayoutCalculatorBinding
import `in`.android.mads_calculator.utils.Calculator
import `in`.android.mads_calculator.utils.toHistory
import `in`.android.mads_calculator.utils.toggleVisibility
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val historyCount = MutableLiveData(0)
    private var lastExpression = ""
    private var lastAnswer = ""
    private var historyMap = mutableMapOf<String, String>()
    lateinit var auth: FirebaseAuth



    private lateinit var binding: LayoutCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        observeHistoryCount()
        super.onCreate(savedInstanceState)
        binding = LayoutCalculatorBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        setupOnClickListeners()
        checkLoginStatus()


    }

    private fun observeHistoryCount() {
        historyCount.observe(this) { count ->

            if (count == 0) {
                binding.history.toggleVisibility(false)
            } else {
                binding.history.toggleVisibility()
                historyMap[lastExpression] = lastAnswer


            }


        }
    }

    private fun setupOnClickListeners() {
        binding.apply {
            one.setOnClickListener(this@MainActivity)
            two.setOnClickListener(this@MainActivity)
            three.setOnClickListener(this@MainActivity)
            four.setOnClickListener(this@MainActivity)
            five.setOnClickListener(this@MainActivity)
            six.setOnClickListener(this@MainActivity)
            seven.setOnClickListener(this@MainActivity)
            eight.setOnClickListener(this@MainActivity)
            nine.setOnClickListener(this@MainActivity)
            zero.setOnClickListener(this@MainActivity)

            plus.setOnClickListener(this@MainActivity)
            minus.setOnClickListener(this@MainActivity)
            multiply.setOnClickListener(this@MainActivity)
            divide.setOnClickListener(this@MainActivity)

            ac.setOnClickListener(this@MainActivity)
            equals.setOnClickListener(this@MainActivity)

            history.setOnClickListener(this@MainActivity)
        }

    }

    private fun checkLoginStatus() {
        auth.currentUser?.let { user ->
            binding.logout.toggleVisibility()
            binding.logout.setOnClickListener {
                auth.signOut()
                finish()
                startActivity(Intent(this, AuthenticationActivity::class.java))
            }
        } ?: binding.logout.toggleVisibility(false)
    }


    override fun onClick(v: View?) {
        binding.apply {
            if (v is AppCompatButton) {
                when (v) {
                    //operators
                    plus, minus, multiply, divide -> {
                        val operator: Spannable = SpannableString(v.text)
                        operator.setSpan(
                            ForegroundColorSpan(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.blue
                                )
                            ),
                            0,
                            operator.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        if (operation.text.isNotEmpty()) {

                            if (operation.text.last() in listOf('+', '-', 'X', '/')) {
                                operation.text = operation.text.dropLast(1)
                            }
                            operation.append(operator)
                        } else if (operationResult.text.isNotEmpty()) {
                            operation.text =
                                (operationResult.text.drop(1).toString().toFloatOrNull()?.toInt()
                                    ?: 0).toString()
                            operation.append(operator)
                            operationResult.text = ""
                        }
                    }


                    ac -> {
                        operation.text = ""; operationResult.text = ""
                    }

                    equals -> {
                        try {
                            if (operation.text.isNotEmpty()) {
                                lastExpression = operation.text.toString()
                                lastExpression = lastExpression.replace("X", "*")
                                operation.text = ""
                                lastAnswer = Calculator.evaluate(lastExpression).toString()
                                operationResult.text = "=".plus(lastAnswer)
                                historyCount.value = (historyCount.value ?: 0) + 1
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            operationResult.text = e.message ?: "Syntax Error"
                        }


                    }

                    else -> {
                        operationResult.text = ""
                        if (operation.text.toString() != "0") {
                            operation.append(v.text.toString())
                        } else {
                            operation.text = v.text.toString()
                        }

                    }

                }

            } else {
                when (v) {
                    history -> {
                        val builder = AlertDialog.Builder(this@MainActivity)
                        builder.setTitle(history.text.toString())
                        loadLocalHistory(builder)
                    }
                }


            }
        }
    }

    private fun loadLocalHistory(builder: AlertDialog.Builder) {
        builder.setMessage(historyMap.toHistory())
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.button_clear_history) { d, w ->
            historyMap.clear()
            historyCount.value = 0
            d.dismiss()
        }


        builder.show()
    }
}







