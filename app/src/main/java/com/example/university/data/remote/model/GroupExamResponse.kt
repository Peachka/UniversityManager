package com.example.university.data.remote.model

data class Exam(
    val daysLeft: Int,
    val id: String,
    val date: String,
    val lecturerName: String,
    val lecturerId: String,
    val room: String,
    val subjectShort: String?,
    val subject: String,
    val group: Any?, // null in this case
    val genericGroupId: String
)

data class GroupExamResponse(
    val paging: Paging,
    val data: List<Exam>
)
