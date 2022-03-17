package com.example.mvpapplicaton.model.cache

import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun getUsers(): Single<List<GithubUser>>
    fun putUsers(users: List<GithubUser>) : Completable
}
