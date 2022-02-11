package com.example.mvpapplicaton.view.main

import android.os.Bundle
import com.example.mvpapplicaton.App
import com.example.mvpapplicaton.R
import com.example.mvpapplicaton.databinding.ActivityMainBinding
import com.example.mvpapplicaton.presenter.main.MainPresenter
import com.example.mvpapplicaton.view.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MvpActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router, App.instance.screen) }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}