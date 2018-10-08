package com.example.christoforos.sandbox.presentation.components.navigation

import android.content.Context
import android.content.Intent
import com.example.christoforos.sandbox.presentation.components.activities.MainActivity

object Navigator {

    fun navigateToMain(context: Context?) {
        if (context != null) {
            val intent = MainActivity.getCallingIntent(context)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }
    }
}