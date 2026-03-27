package com.madhan.feature_tinder.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.composable.TopBar
import com.madhan.feature_tinder.util.convertMillisToDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfileScreen03(
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            leftSlot = {
                Icon(
                    painter = painterResource(R.drawable.ic_return),
                    contentDescription = ""
                )
            },
            middleSlot = {
                Text(
                    text = "Modify your profile"
                )
            }
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "Description")
            },
            modifier = Modifier
                .fillMaxWidth(),
            minLines = 5
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = "City")
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("DOB") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .height(64.dp)
            )

            if (showDatePicker) {
                Popup(
                    onDismissRequest = { showDatePicker = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 64.dp)
                            .shadow(elevation = 4.dp)
                            .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                    }
                }
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.Start),
            text = "Add more photos"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .border(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Gray,
                        width = 1.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = ""
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .border(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Gray,
                        width = 1.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = ""
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .border(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Gray,
                        width = 1.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = ""
                )
            }
        }
        Button(
            onClick = {}
        ) {
            Text(
                text = "Update"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateProfileScreen03Preview() {
    UpdateProfileScreen03()
}