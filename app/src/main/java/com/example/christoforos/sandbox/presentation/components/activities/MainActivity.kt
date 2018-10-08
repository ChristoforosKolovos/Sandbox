package com.example.christoforos.sandbox.presentation.components.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.christoforos.sandbox.R
import com.example.christoforos.sandbox.presentation.presenters.MainPresenter
import com.example.christoforos.sandbox.presentation.screens.MainScreen
import com.example.christoforos.sandbox.presentation.utils.bind

class MainActivity : AppCompatActivity(), Activity, MainScreen {

    private val btn: View by bind(R.id.btn)

    //-------------------Navigation Methods-------------------
    companion object {
        @JvmStatic
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    //-------------------Variables-------------------
    private lateinit var presenter: MainPresenter


    //-------------------Screen Variables-------------------
    override val appContext: Context
        get() = applicationContext

    override val screenContext: Context
        get() = this


    //-------------------Activity Methods-------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initPresenter()
    }

    override fun initViews() {
        btn.setOnClickListener { presenter.loginButtonClicked() }
    }

    override fun initPresenter() {
        presenter = MainPresenter(this)
    }


    //-------------------Screen Methods-------------------
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError() {
    }
}