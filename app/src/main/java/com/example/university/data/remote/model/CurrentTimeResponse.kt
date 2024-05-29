package com.example.university.data.remote.model

data class CurrentTime(
    var currentWeek: Int,
    val currentDay: Int,
    val currentLesson: Int
)

data class CurrentTimeResponse(
    val paging: Any?, // null in this case
    val data: CurrentTime
)
