package com.example.university.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.university.data.remote.model.DaySchedule
import com.example.university.data.remote.model.GroupSchedule
import com.example.university.data.remote.model.Pair
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.university.data.remote.model.CurrentTime
import com.example.university.data.remote.model.Group
import com.example.university.data.remote.model.getNameById

@Composable
fun ScheduleScreen(
    groups: List<Group>,
    groupSchedule: GroupSchedule?,
    currentTime: CurrentTime?,
    updateGroupSchedule: (String) -> Unit,
    selectedDay: String,
    selectedWeek: Int,
    selectedGroup: Group?,
    updateSelectedDay: (String) -> Unit,
    updateSelectedWeek: (Int) -> Unit,
    updateSelectedGroup: (Group) -> Unit
) {
    if (groupSchedule == null || currentTime == null || selectedGroup == null) {
        Text("Loading...")
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GroupDropdown(
            groups = groups,
            selectedGroup = selectedGroup.name,
            onGroupSelected = { group ->
                updateSelectedGroup(group)
                updateGroupSchedule(group.id)
            }
        )
        WeekButton(changeWeek = { week -> updateSelectedWeek(week) }, selectedWeek)
        DayButton(selectedDay = selectedDay, onDaySelected = { day -> updateSelectedDay(day) })
        ScheduleDesk(
            groupSchedule = groupSchedule,
            currentTime = currentTime,
            selectedDay = selectedDay,
            selectedWeek = selectedWeek
        )
    }
}


@Composable
fun ScheduleDesk(
    groupSchedule: GroupSchedule,
    currentTime: CurrentTime,
    selectedDay: String,
    selectedWeek: Int

) {
    val weekSchedule: List<DaySchedule> =
        if (selectedWeek == 0) {
            groupSchedule.scheduleFirstWeek
        } else {
            groupSchedule.scheduleSecondWeek
        }

    val daySchedule = weekSchedule.firstOrNull { it.day == selectedDay } ?: return

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(daySchedule.pairs.sortedBy { it.time.split(".")[0].toInt() }) { lessonPair ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(0.5f)) {
                        Text(text = "Time: ${lessonPair.time}", fontSize = 14.sp)
                        Text(
                            text = "Name: ${lessonPair.name}",
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(text = "Place: ${lessonPair.place}", fontSize = 14.sp)
                        Text(text = "Type: ${lessonPair.type}", fontSize = 14.sp)
                    }
                    Column(modifier = Modifier.weight(0.5f)) {
                        Text(text = "Teacher: ${lessonPair.teacherName}", fontSize = 14.sp)
                        Text(text = "Tag: ${lessonPair.tag}", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun WeekButton(changeWeek: (Int) -> Unit, selectedWeek: Int) {
    var selectedTabIndex by remember { mutableStateOf(selectedWeek) }

    val tabs = listOf("Перший тиждень", "Другий тиждень")

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = Color.Yellow
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(tab, textAlign = TextAlign.Center) },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    selectedContentColor = Color.Yellow,
                    unselectedContentColor = Color.Gray
                )
            }
        }
        when (selectedTabIndex) {
            0 -> changeWeek(0)
            1 -> changeWeek(1)
        }
    }

}

@Composable
fun DayButton(selectedDay: String, onDaySelected: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularButton(
            title = "Пн",
            isSelected = selectedDay == "Пн",
            onClick = { onDaySelected("Пн") })
        CircularButton(
            title = "Вв",
            isSelected = selectedDay == "Вв",
            onClick = { onDaySelected("Вв") })
        CircularButton(
            title = "Ср",
            isSelected = selectedDay == "Ср",
            onClick = { onDaySelected("Ср") })
        CircularButton(
            title = "Чт",
            isSelected = selectedDay == "Чт",
            onClick = { onDaySelected("Чт") })
        CircularButton(
            title = "Пт",
            isSelected = selectedDay == "Пт",
            onClick = { onDaySelected("Пт") })
        CircularButton(
            title = "Сб",
            isSelected = selectedDay == "Сб",
            onClick = { onDaySelected("Сб") })
    }
}

@Composable
fun CircularButton(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color.Yellow else MaterialTheme.colors.onPrimary
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(50.dp)
            .padding(4.dp) // You can adjust the size as needed
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GroupDropdown(
    groups: List<Group>,
    selectedGroup: String,
    onGroupSelected: (Group) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedGroupName by remember { mutableStateOf(selectedGroup) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedGroupName,
            onValueChange = { selectedGroupName = it },
            readOnly = true,
            placeholder = { Text(text = "Select a group") },
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onPrimary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            groups.forEach { group ->
                DropdownMenuItem(
                    onClick = {
                        selectedGroupName = group.name
                        onGroupSelected(group)
                        expanded = false
                    }
                ) {
                    Text(text = group.name)
                }
            }
        }
    }
}

//
//@Preview
//@Composable
//fun PreviewScheduleScreen() {
//    var groupName: List<Group> = listOf()
//    ScheduleScreen(groupName, generateMockGroupSchedule(), CurrentTime(0, 2, 2), { "" })
//}


fun generateMockGroupSchedule(): GroupSchedule {
    val mockPairs = listOf(
        com.example.university.data.remote.model.Pair(
            teacherName = "Dr. John Doe",
            lecturerId = "lecturer-001",
            type = "Lecture",
            time = "08:00 - 09:30",
            name = "Mathematics",
            place = "Room 101",
            tag = "lec"
        ),
        com.example.university.data.remote.model.Pair(
            teacherName = "Prof. Jane Smith",
            lecturerId = "lecturer-002",
            type = "Lab",
            time = "10:00 - 11:30",
            name = "Physics",
            place = "Room 102",
            tag = "lab"
        ),
        Pair(
            teacherName = "Dr. Alice Johnson",
            lecturerId = "lecturer-003",
            type = "Practical",
            time = "12:00 - 13:30",
            name = "Chemistry",
            place = "Room 103",
            tag = "prac"
        )
    )

    val mockDaySchedule1 = DaySchedule(
        day = "Пн",
        pairs = mockPairs
    )

    val mockDaySchedule2 = DaySchedule(
        day = "Вв",
        pairs = mockPairs
    )

    val mockDaySchedule3 = DaySchedule(
        day = "Ср",
        pairs = mockPairs
    )

    val mockScheduleFirstWeek = listOf(mockDaySchedule1, mockDaySchedule2, mockDaySchedule3)
    val mockScheduleSecondWeek = listOf(mockDaySchedule1, mockDaySchedule2, mockDaySchedule3)

    return GroupSchedule(
        groupCode = "IO-05",
        scheduleFirstWeek = mockScheduleFirstWeek,
        scheduleSecondWeek = mockScheduleSecondWeek
    )
}