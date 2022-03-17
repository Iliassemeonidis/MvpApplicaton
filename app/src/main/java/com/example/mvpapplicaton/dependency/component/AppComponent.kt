package com.example.mvpapplicaton.dependency.component

import com.example.mvpapplicaton.dependency.app.AppModule
import com.example.mvpapplicaton.dependency.ciceroni.CiceroneModule
import com.example.mvpapplicaton.dependency.modul.api.ApiModule
import com.example.mvpapplicaton.dependency.modul.cache.CacheModule
import com.example.mvpapplicaton.dependency.modul.repository.RepoModule
import com.example.mvpapplicaton.presenter.main.MainPresenter
import com.example.mvpapplicaton.presenter.user.UsersPresenter
import com.example.mvpapplicaton.view.main.MvpActivity
import com.example.mvpapplicaton.view.user.UsersFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity:MvpActivity)
    fun inject(mainPresenter:MainPresenter)
    fun inject(usersPresenter:UsersPresenter)
    fun inject(usersFragment: UsersFragment)
}