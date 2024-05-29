package com.example.university.data.remote.model

data class Lecturer(
    val id: String,
    val name: String
)

data class LecturerResponse(
    val paging: Paging,
    val data: List<Lecturer>
)
