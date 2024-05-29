package com.example.university.data.remote

import com.example.university.data.remote.model.CurrentTime
import com.example.university.data.remote.model.Exam
import com.example.university.data.remote.model.Group
import com.example.university.data.remote.model.GroupSchedule
import com.example.university.data.remote.model.Lecturer
import com.example.university.data.remote.model.LecturerSchedule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepository @Inject constructor(private val apiService: ScheduleApiService) {

    suspend fun getGroupSchedule(groupId: String): GroupSchedule {
        val response = apiService.getGroupSchedule(groupId)
        return response.data
    }

    suspend fun getGroups(): List<Group> {
        val groupResponse = apiService.getGroups()
        return groupResponse.data
    }

    suspend fun getLecturers(): List<Lecturer> {
        val lecturerResponse = apiService.getLecturers()
        return lecturerResponse.data
    }

    suspend fun getLecturerSchedule(lecturerId: Int): LecturerSchedule {
        val response = apiService.getLecturerSchedule(lecturerId)
        return response.data
    }

    suspend fun getGroupExams(groupId: Int): List<Exam> {
        val response = apiService.getGroupExams(groupId)
        return response.data
    }

    suspend fun getCurrentDayAndWeek(): CurrentTime {
        val response = apiService.getCurrentDayAndWeek()
        return response.data
    }
}
