package com.example.mvpapplicaton.presenter.user

import com.example.mvpapplicaton.model.reposetory.GithubUsersRepo
import com.example.mvpapplicaton.presenter.navigation.IScreens
import com.example.mvpapplicaton.view.main.adapter.IUserListPresenter
import com.example.mvpapplicaton.view.main.adapter.UserItemView
import com.example.mvpapplicaton.view.user.GithubUser
import com.example.mvpapplicaton.view.user.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val screens: IScreens,
    private val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
             val item = users[view.pos]
             view.setLogin(item.toString())
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    private fun onItemClick(user: GithubUser) {
        router.navigateTo(screens.navigateTo(user))
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { it ->
            onItemClick(usersListPresenter.users[it.pos])
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}
