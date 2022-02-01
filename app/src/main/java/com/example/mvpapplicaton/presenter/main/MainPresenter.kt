package com.example.mvpapplicaton.presenter.main

import com.example.mvpapplicaton.presenter.navigation.IScreens
import com.example.mvpapplicaton.view.main.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }

    // TODO удалить после проверки дз медодичка 1
//    fun contClick(id: Int, counter: Counter) {
//        when (counter) {
//            Counter.BtnCounter1 -> {
//                takeValuesFromModel(id, Counter.BtnCounter1)
//            }
//            Counter.BtnCounter2 -> {
//                takeValuesFromModel(id, Counter.BtnCounter2)
//            }
//            Counter.BtnCounter3 -> {
//                takeValuesFromModel(id, Counter.BtnCounter3)
//            }
//        }
//    }
//
//    private fun takeValuesFromModel(int: Int, type: Counter) {
//        val item = model.next(int)
//        viewState.setButtonText(type, item.toString())
//    }

}