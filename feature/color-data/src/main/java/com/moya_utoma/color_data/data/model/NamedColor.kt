package com.moya_utoma.color_data.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.moya_utoma.quizer.model.QuizAnswer

@Entity
data class NamedColor(
    @SerializedName("hexCode")
    override val description: String, // contains HEX value

    @SerializedName("name")
    @PrimaryKey override val name: String
) : QuizAnswer()
