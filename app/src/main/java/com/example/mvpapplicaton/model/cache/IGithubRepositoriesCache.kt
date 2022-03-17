package com.example.mvpapplicaton.model.cache

import com.example.mvpapplicaton.model.reposetory.githubuser.GithubRepository
import com.example.mvpapplicaton.view.user.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
    fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable
}