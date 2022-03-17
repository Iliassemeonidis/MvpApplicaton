package com.example.mvpapplicaton.dependency.modul.repository

import com.example.mvpapplicaton.model.data.IDataSource
import com.example.mvpapplicaton.model.db.Database
import com.example.mvpapplicaton.model.reposetory.githubuser.IGithubRepositoriesRepo
import com.example.mvpapplicaton.model.reposetory.githubuser.RetrofitGithubRepositoriesRepo
import com.example.mvpapplicaton.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun userRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        database: Database
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, database)



}