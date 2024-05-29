package com.example.university.ui.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.data.remote.ScheduleRepository
import com.example.university.data.remote.model.CurrentTime
import com.example.university.data.remote.model.Group
import com.example.university.data.remote.model.GroupSchedule
import com.example.university.data.remote.model.Lecturer
import com.example.university.ui.log_in.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: ScheduleRepository,
    private val userDao: UserRepositoryImpl
) : ViewModel() {

    private val _groupSchedule = MutableStateFlow<GroupSchedule?>(null)
    val groupSchedule: StateFlow<GroupSchedule?> get() = _groupSchedule

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups: StateFlow<List<Group>> get() = _groups

    private val _lecturers = MutableStateFlow<List<Lecturer>>(emptyList())
    val lecturers: StateFlow<List<Lecturer>> get() = _lecturers

    private val _currentTime = MutableStateFlow<CurrentTime?>(null)
    val currentTime: StateFlow<CurrentTime?> get() = _currentTime

    private val _selectedDay = MutableStateFlow("Пн")
    val selectedDay: StateFlow<String> get() = _selectedDay

    private val _selectedWeek = MutableStateFlow(0)
    val selectedWeek: StateFlow<Int> get() = _selectedWeek

    private val _selectedGroup = MutableStateFlow<Group?>(null)
    val selectedGroup: StateFlow<Group?> get() = _selectedGroup

    init {
        viewModelScope.launch {
            try {
                fetchGroups()

                val userId = userDao.getUserIdFlow().filterNotNull().first()
                val user = userDao.getAll().map { it.find { it.uid == userId } }.first()
                val groupId = repository.getGroups().filter { it.name == user?.group }.map { it.id }.first()
                updateSelectedGroup(repository.getGroups().first { it.name == user?.group })
                updateGroupSchedule(groupId)

                val time = repository.getCurrentDayAndWeek()
                _currentTime.value = time
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun fetchGroups() {
        viewModelScope.launch {
            try {
                val groupList = repository.getGroups()
                _groups.value = groupList
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun updateGroupSchedule(groupId: String) {
        viewModelScope.launch {
            try {
                val schedule = repository.getGroupSchedule(groupId)
                _groupSchedule.value = schedule
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun updateSelectedDay(newDay: String) {
        _selectedDay.value = newDay
    }

    fun updateSelectedWeek(newWeek: Int) {
        _selectedWeek.value = newWeek
    }

    fun updateSelectedGroup(newGroup: Group?) {
        _selectedGroup.value = newGroup
    }
}
