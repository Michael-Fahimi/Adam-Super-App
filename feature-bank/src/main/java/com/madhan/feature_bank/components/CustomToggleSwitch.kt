package com.madhan.feature_bank.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhan.adamsuperapp.ui.theme.SecondaryColor

@Composable
fun CustomToggleSwitch(checkedValue: Boolean) {
    var isChecked by remember { mutableStateOf(checkedValue) }

    Switch(
        checked = isChecked,
        onCheckedChange = { isChecked = it },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Transparent,
            uncheckedThumbColor = Color.Transparent,
            checkedTrackColor = SecondaryColor,
            uncheckedBorderColor = Color.Transparent,
            uncheckedTrackColor = Color.LightGray
        ),
        thumbContent = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomToggleSwitch() {
    CustomToggleSwitch(true)
}