package net.youapps.converter.backend.models

import java.io.Serializable

data class TrimTimestamps(
    val startTimeStamp: String,
    val endTimeStamp: String
) : Serializable
