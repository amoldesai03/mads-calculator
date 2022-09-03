package `in`.android.mads_calculator.utils

import java.util.*

object Calculator {
    fun evaluate(expression: String): Float {
        val tokens = expression.toCharArray()
        val values = Stack<Float>()
        val ops = Stack<Char>()
        var i = 0
        while (i < tokens.size) {
            if (tokens[i] == ' ') {
                i++
                continue
            }
            if (tokens[i] in '0'..'9'
            ) {
                val stringBuffer = StringBuffer()
                while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9') stringBuffer.append(tokens[i++])
                values.push(stringBuffer.toString().toFloat())
                i--
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() &&
                    hasPrecedence(
                        tokens[i],
                        ops.peek()
                    )
                ) values.push(
                    applyOp(
                        ops.pop(),
                        values.pop(),
                        values.pop()
                    )
                )


                ops.push(tokens[i])
            }
            i++
        }


        while (!ops.empty()) values.push(
            applyOp(
                ops.pop(),
                values.pop(),
                values.pop()
            )
        )


        return values.pop()
    }


    private fun hasPrecedence(
        op1: Char, op2: Char
    ): Boolean {
        if (op1 == '*' && (op2 == '+' || op2 == '/' || op2 == '-')) return false
        return if (op1 == '+' && (op2 == '/' || op2 == '-')) false else op1 != '/' || op2 != '-'
    }


    private fun applyOp(
        op: Char,
        b: Float, a: Float
    ): Float {
        when (op) {
            '+' -> return a + b
            '-' -> return a - b
            '*' -> return a * b
            '/' -> {
                if (b == 0f) throw UnsupportedOperationException(
                    "Cannot divide by zero"
                )
                return a / b
            }
        }
        return 0f
    }
}