package com.example.mvpapplicaton.model.db.table

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
//TODO разобраться воообще что тут к чему
@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubRepository::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGithubRepository(
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: Int,
    var userId: String
)