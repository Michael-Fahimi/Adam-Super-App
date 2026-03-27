package com.madhan.core.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.core.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommonCalendar(startDate: LocalDate?, endDate: LocalDate?, onDateSelected: (LocalDate) -> Unit, onBackPress: () -> Unit) {
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackPress() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFFFF8C00))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Choose date", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Check-in and Check-out Dates
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Check in", fontSize = 14.sp, color = Color(0xFFFF8C00))
                Text(
                    text = startDate?.toString() ?: "Select Date",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Text(text = "3D", fontSize = 14.sp, color = Color.White, modifier = Modifier
                .background(Color(0xFF4CAF50), RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp))
            Column(horizontalAlignment = Alignment.End) {
                Text(text = "Check out", fontSize = 14.sp, color = Color(0xFFFF8C00))
                Text(
                    text = endDate?.toString() ?: "Select Date",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calendar Component
        CalendarView(
            startDate = startDate,
            endDate = endDate,
            onDateSelected = { selectedDate ->
                if (startDate == null || (startDate != null && endDate != null)) {
                    startDate = selectedDate
                    endDate = null
                } else if (selectedDate > startDate!!) {
                    endDate = selectedDate
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Next Button

        CustomButton(
            onClick = {
                if (startDate != null && endDate != null) {
                    //
                }
            },
            buttonText = "Next"
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(startDate: LocalDate?, endDate: LocalDate?, onDateSelected: (LocalDate) -> Unit) {
    val today = LocalDate.now()
    val firstDayOfMonth = today.withDayOfMonth(1)
    val daysInMonth = today.lengthOfMonth()
    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7

    Column(modifier = Modifier.fillMaxWidth()) {
        // Month Navigation
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { /* Handle Previous Month */ }) {
                Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Previous Month")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = today.month.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { /* Handle Next Month */ }) {
                Icon(painter = painterResource(id = R.drawable.next), contentDescription = "Next Month")
            }
        }

        // Days of the Week Header
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("S", "M", "T", "W", "T", "F", "S").forEach {
                Text(text = it, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Display Dates with Proper Alignment
        Column {
            var currentDay = 1 - startDayOfWeek // Start from the correct weekday

            while (currentDay <= daysInMonth) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (day in 0 until 7) { // Week has 7 days
                        val date = if (currentDay in 1..daysInMonth) {
                            LocalDate.of(today.year, today.month, currentDay)
                        } else {
                            null
                        }

                        val isSelected = date != null && (
                                date == startDate || date == endDate ||
                                        (startDate != null && endDate != null && date in startDate..endDate)
                                )

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    if (isSelected) Color(0xFF4CAF50) else Color.Transparent,
                                    RoundedCornerShape(18.dp)
                                )
                                .clickable { date?.let { onDateSelected(it) } },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (currentDay in 1..daysInMonth) currentDay.toString() else "",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }

                        currentDay++ // Move to the next day
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CommonCalendarPreview() {
    val startDate = LocalDate.of(2023, 3, 20)
    val endDate = LocalDate.of(2023, 3, 22)

    CommonCalendar(
        startDate = startDate,
        endDate = endDate,
        onDateSelected = { /* Simulate Date Selection */ },
        onBackPress = { /* Simulate Back Button Click */ }
    )
}