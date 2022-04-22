package com.cherry.guessthething.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cartoon(
    val posterImageUrl: String,
    @PrimaryKey val name: String
)