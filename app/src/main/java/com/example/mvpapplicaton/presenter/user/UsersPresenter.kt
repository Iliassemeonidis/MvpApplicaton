package com.example.mvpapplicaton.presenter.user

import com.example.mvpapplicaton.model.reposetory.GithubUsersRepo
import com.example.mvpapplicaton.view.adapter.IUserListPresenter
import com.example.mvpapplicaton.view.adapter.UserItemView
import com.example.mvpapplicaton.view.user.GithubUser
import com.example.mvpapplicaton.view.user.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {

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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
//TODO: переход на экран пользователя c помощью router.navigateTo }
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
