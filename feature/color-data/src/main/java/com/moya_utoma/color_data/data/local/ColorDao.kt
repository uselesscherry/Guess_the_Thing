package com.moya_utoma.color_data.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moya_utoma.color_data.data.model.NamedColor

@Dao
interface ColorDao {

    @Query("SELECT * FROM namedcolor")
    suspend fun getColors(): List<NamedColor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(colors: List<NamedColor>)

}