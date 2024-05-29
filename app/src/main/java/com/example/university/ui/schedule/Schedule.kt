package com.example.university.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Schedule(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val currentTime by viewModel.currentTime.collectAsState()
    val groupSchedule by viewModel.groupSchedule.collectAsState()
    val groups by viewModel.groups.collectAsState()
    val selectedDay by viewModel.selectedDay.collectAsState()
    val selectedWeek by viewModel.selectedWeek.collectAsState()
    val selectedGroup by viewModel.selectedGroup.collectAsState()

    ScheduleScreen(
        groups = groups,
        groupSchedule = groupSchedule,
        currentTime = currentTime,
        updateGroupSchedule = { groupId -> viewModel.updateGroupSchedule(groupId) },
        selectedDay = selectedDay,
        selectedWeek = selectedWeek,
        selectedGroup = selectedGroup,
        updateSelectedDay = { newDay -> viewModel.updateSelectedDay(newDay) },
        updateSelectedWeek = { newWeek -> viewModel.updateSelectedWeek(newWeek) },
        updateSelectedGroup = { newGroup -> viewModel.updateSelectedGroup(newGroup) }
    )
}
