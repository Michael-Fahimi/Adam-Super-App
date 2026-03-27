package com.madhan.feature_tinder.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.composable.CustomButton
import com.madhan.feature_tinder.composable.TitleText
import com.madhan.feature_tinder.composable.TopBar
import com.madhan.feature_tinder.util.convertMillisToDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProfileScreen03(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""
    var description by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    if (datePickerState.selectedDateMillis != null) {
        selectedDate = convertMillisToDate(datePickerState.selectedDateMillis!!)
        if (showDatePicker) {
            showDatePicker = false;
        }
    }
    Column(modifier = modifier) {
        TopBar(
            leftSlot = {
                Icon(
                    painter = painterResource(R.drawable.ic_return),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            navController!!.navigate(TinderRoute.TakePhoto.route)  {
                                popUpTo(TinderRoute.AddProfile.route) {
                                    inclusive = true
                                }
                            }
                        }
                        .scale(2f)
                        .size(32.dp),
                    tint = PrimaryColor
                )
            },
            middleSlot = {
                TitleText(
                    text = "Complete your profile",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = {
                    Text(text = "Description")
                },
                modifier = Modifier
                    .fillMaxWidth(),
                minLines = 5,
                maxLines = 5


            )
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = {
                    Text(text = "City")
                },
                modifier = Modifier
                    .fillMaxWidth(),
                minLines = 1,
                maxLines = 1
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
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
                ,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ItemBox(modifier = Modifier.weight(1f))
                ItemBox(modifier = Modifier.weight(1f))
                ItemBox(modifier = Modifier.weight(1f))
            }
            CustomButton(
                text = "Next",
                onClick = {
                    navController!!.navigate(TinderRoute.EnableLocation.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 64.dp)
            )
        }
    }
}

@Composable
fun ItemBox(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int? = null
) {
    Box(
        modifier = modifier
            .aspectRatio(2f/3f)
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

@Preview(showBackground = true)
@Composable
fun CompleteProfileScreen03Preview() {
//    AddProfileScreen03()
}

