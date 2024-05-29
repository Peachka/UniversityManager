package com.example.university.data.remote.model

data class Pair(
    val teacherName: String,
    val lecturerId: String,
    val type: String,
    val time: String,
    val name: String,
    val place: String,
    val tag: String
)

data class DaySchedule(
    val day: String,
    val pairs: List<Pair>
)

data class GroupSchedule(
    val groupCode: String,
    val scheduleFirstWeek: List<DaySchedule>,
    val scheduleSecondWeek: List<DaySchedule>
)

data class GroupScheduleResponse(
    val paging: Any?, // null in this case
    val data: GroupSchedule
)
