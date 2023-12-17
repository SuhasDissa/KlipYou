package net.youapps.converter.backend.models

import java.io.Serializable

data class ReverseData(
    val audio: Boolean,
    val video: Boolean
) : Serializable
