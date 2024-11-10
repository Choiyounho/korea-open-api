package com.soten.openapi.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val id: Int = 0,
    val nextKey: Int,
    val lastUpdated: Long
)
