package com.example.mvpapplicaton

import android.app.Application
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.presenter.navigation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }

    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val router get() = cicerone.router

     val screen get() = AndroidScreens()
}