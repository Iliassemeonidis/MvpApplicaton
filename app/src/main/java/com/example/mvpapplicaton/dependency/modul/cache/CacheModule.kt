package com.example.mvpapplicaton.dependency.modul.cache

import androidx.room.Room
import com.example.mvpapplicaton.App
import com.example.mvpapplicaton.model.cache.IGithubUsersCache
import com.example.mvpapplicaton.model.cache.RoomGithubUsersCache
import com.example.mvpapplicaton.model.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache =
        RoomGithubUsersCache(database)

}