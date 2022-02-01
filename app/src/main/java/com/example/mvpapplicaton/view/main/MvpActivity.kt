package com.example.mvpapplicaton.view.main

import android.os.Bundle
import com.example.mvpapplicaton.App
import com.example.mvpapplicaton.R
import com.example.mvpapplicaton.databinding.ActivityMainBinding
import com.example.mvpapplicaton.presenter.main.MainPresenter
import com.example.mvpapplicaton.presenter.navigation.AndroidScreens
import com.example.mvpapplicaton.view.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MvpActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }
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

    // TODO удалить после проверки, сделал дз по первой методички
//    private fun onClickButtons() {
//        vb?.btnCounter1?.setOnClickListener {
//            println(it.id.toString())
//            presenter.contClick(0, Counter.BtnCounter1)
//        }
//        vb?.btnCounter2?.setOnClickListener { presenter.contClick(1, Counter.BtnCounter2) }
//        vb?.btnCounter3?.setOnClickListener { presenter.contClick(2, Counter.BtnCounter3) }
//    }
//
//    override fun setButtonText(index: Counter, value: String) {
//        when (index) {
//            Counter.BtnCounter1 -> vb?.btnCounter1?.text = value
//            Counter.BtnCounter2 -> vb?.btnCounter2?.text = value
//            Counter.BtnCounter3 -> vb?.btnCounter3?.text = value
//        }
//    }
}