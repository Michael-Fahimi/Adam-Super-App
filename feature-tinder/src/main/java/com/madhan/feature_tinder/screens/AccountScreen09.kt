package com.madhan.feature_tinder.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.composable.TopBar

@Composable
fun AccountScreen09(
    modifier: Modifier = Modifier
) {
    var distanceSliderPosition by remember { mutableStateOf(0f)}
    var ageSliderPosition by remember { mutableStateOf(0f)}

    Column(
        modifier = modifier
            .fillMaxSize()
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
                    text = "Account"
                )
            },
            rightSlot = {
                Text(
                    modifier = Modifier
                        .clickable {  },
                    text = "Clear"
                )
            }
        )
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Distance"
                )
                Spacer(modifier = Modifier.width(16.dp))
                HorizontalDivider(thickness = 2.dp)
            }
            Slider(
                value = distanceSliderPosition,
                onValueChange = { distanceSliderPosition = it }
            )
            Text(
                text = distanceSliderPosition.toString()
            )
        }
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Age"
                )
                Spacer(modifier = Modifier.width(16.dp))
                HorizontalDivider(thickness = 2.dp)
            }
            Slider(
                value = ageSliderPosition,
                onValueChange = { ageSliderPosition = it }
            )
            Text(
                text = ageSliderPosition.toString()
            )
        }
        Row {
            Text(
                text = "Unit"
            )
            Row {
                Text(
                    text = "Miles"
                )
                Switch(
                    checked = true,
                    onCheckedChange = {}
                )
                Text(
                    text = "Km"
                )
            }
        }
        Row {
            Text(
                text = "Visibility"
            )
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_no_visibility),
                    contentDescription = ""
                )
                Switch(
                    checked = true,
                    onCheckedChange = {}
                )
                Icon(
                    painter = painterResource(R.drawable.ic_visible),
                    contentDescription = ""
                )
            }
        }
        Button(
            onClick = {}
        ) {
            Row {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_account),
                        contentDescription = ""
                    )
                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                    )
                    Text(
                        text = "Modify your profile"
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = ""
                )
            }
        }
        Button(
            onClick = {}
        ) {
            Row {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_trash),
                        contentDescription = ""
                    )
                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                    )
                    Text(
                        text = "Modify your profile"
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountScreen09Preview() {
    AccountScreen09()
}