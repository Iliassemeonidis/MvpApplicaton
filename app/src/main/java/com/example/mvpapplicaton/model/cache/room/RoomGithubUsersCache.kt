package com.example.mvpapplicaton.model.cache.room

import com.example.mvpapplicaton.model.cache.IGithubUsersCache
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.model.db.table.RoomGithubUser
import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(val db: Database) : IGithubUsersCache {

    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }

    override fun putUsers(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user -> RoomGithubUser(user.id ?: "", user.login ?:"", user.avatarUrl ?: "", user.reposUrl ?: "") }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}