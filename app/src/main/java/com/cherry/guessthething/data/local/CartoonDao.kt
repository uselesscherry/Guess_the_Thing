package com.cherry.guessthething.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherry.guessthething.domain.model.Cartoon

@Dao
interface CartoonDao {

    @Query("SELECT * FROM cartoon")
    suspend fun getCartoons(): List<Cartoon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCartoons(cartoons: List<Cartoon>)

}
