package com.example.university.data.remote

import com.example.university.data.remote.model.CurrentTimeResponse
import com.example.university.data.remote.model.GroupExamResponse
import com.example.university.data.remote.model.GroupResponse
import com.example.university.data.remote.model.GroupScheduleResponse
import com.example.university.data.remote.model.LecturerResponse
import com.example.university.data.remote.model.LecturerScheduleResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApiService {
    @GET("schedule/lessons")
    suspend fun getGroupSchedule(@Query("groupId") groupId: String): GroupScheduleResponse

    @GET("schedule/groups")
    suspend fun getGroups(): GroupResponse


    @GET("schedule/lecturer/list")
    suspend fun getLecturers(): LecturerResponse

    @GET("schedule/lecturer")
    suspend fun getLecturerSchedule(@Query("lecturerId") lecturerId: Int): LecturerScheduleResponse

    @GET("exams/group")
    suspend fun getGroupExams(@Query("groupId") groupId: Int): GroupExamResponse

    @GET("time/current")
    suspend fun getCurrentDayAndWeek(): CurrentTimeResponse
}