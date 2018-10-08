package com.example.christoforos.sandbox.domain.utils

class Uid {
    companion object {
        fun generate(): String {
            var result: String = ""
            for (i in 1 until 20) {
                result += (10..100).random()
                result += ('A'..'Z').random()
                result += ('a'..'z').random()
                result += randomSymbol()
            }
            return result.toCharArray().toList().shuffled().joinToString("")
        }
    }
}