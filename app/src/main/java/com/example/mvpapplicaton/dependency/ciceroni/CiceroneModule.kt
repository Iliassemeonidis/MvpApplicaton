package com.example.mvpapplicaton.dependency.ciceroni

import com.example.mvpapplicaton.presenter.navigation.AndroidScreens
import com.example.mvpapplicaton.presenter.navigation.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    var cicerone: Cicerone<Router> = Cicerone.create()

    fun ciceroni(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder():NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router  = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()


}