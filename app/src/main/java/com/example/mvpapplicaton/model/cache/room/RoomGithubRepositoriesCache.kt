package com.example.mvpapplicaton.model.cache.room

import com.example.mvpapplicaton.model.cache.IGithubRepositoriesCache
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.model.db.table.RoomGithubRepository
import com.example.mvpapplicaton.model.reposetory.githubuser.GithubRepository
import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(val db: Database) : IGithubRepositoriesCache {

    override fun getUserRepos(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException("No such user in cache")
        return@fromCallable db.repositoryDao.findForUser(roomUser.id )
            .map { GithubRepository(it.id, it.name, it.forksCount) }

    }.subscribeOn(Schedulers.io())

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>) = Completable.fromAction {
        val roomUser =   db.userDao.findByLogin(user.login)  ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(it.id, it.name , it.forksCount , roomUser.id )
        }
        db.repositoryDao.insert(roomRepos)

    }.subscribeOn(Schedulers.io())

}