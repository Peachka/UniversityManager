package com.example.university.data.remote.model

data class LecturerPair(
    val group: String,
    val type: String,
    val time: String,
    val name: String,
    val place: String,
    val tag: String
)

data class LecturerDaySchedule(
    val day: String,
    val pairs: List<LecturerPair>
)

data class LecturerSchedule(
    val lecturerName: String,
    val scheduleFirstWeek: List<LecturerDaySchedule>,
    val scheduleSecondWeek: List<LecturerDaySchedule>
)

data class LecturerScheduleResponse(
    val paging: Any?, // null in this case
    val data: LecturerSchedule
)
