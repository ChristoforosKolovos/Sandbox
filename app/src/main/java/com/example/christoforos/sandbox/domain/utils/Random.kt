package com.example.christoforos.sandbox.domain.utils

import java.util.*

private val symbols : List<Char> = listOf('!','@','#','$','%','^','&','*','(',')','_','+','-','=','`','~',',','.','/','<','>','?',';','|');

fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) +  start

fun ClosedRange<Char>.random(): Char =
        (Random().nextInt(endInclusive.toInt() + 1 - start.toInt()) + start.toInt()).toChar()

fun randomSymbol() =
    symbols.shuffled().last()

