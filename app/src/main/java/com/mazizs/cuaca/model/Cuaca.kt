package com.mazizs.cuaca.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cuaca(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)