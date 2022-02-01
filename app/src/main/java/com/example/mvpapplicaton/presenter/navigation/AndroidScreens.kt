package com.example.mvpapplicaton.presenter.navigation

import com.example.mvpapplicaton.view.user.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users()= FragmentScreen { UsersFragment.newInstance() }
}