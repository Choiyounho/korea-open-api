package com.soten.openapi.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soten.openapi.data.local.db.entity.RemoteKey

@Dao
interface RemoteKeyDao {

    @Query("SELECT * FROM remote_keys WHERE id = 0")
    suspend fun getRemoteKey(): RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(remoteKey: RemoteKey)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}