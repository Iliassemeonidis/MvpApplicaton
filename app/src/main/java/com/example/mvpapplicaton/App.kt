package com.example.mvpapplicaton

import android.app.Application
import com.example.mvpapplicaton.dependency.app.AppModule
import com.example.mvpapplicaton.dependency.component.AppComponent
import com.example.mvpapplicaton.dependency.component.DaggerAppComponent
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.presenter.navigation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}