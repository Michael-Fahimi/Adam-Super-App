package com.madhan.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor

@Composable
fun CustomButton(buttonText: String, onClick: () -> Unit = {}){
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 2.dp,
                hoveredElevation = 10.dp
            ),
            modifier = Modifier
                .width(257.dp)
                .height(56.dp)
        ) {
            Text(
                text = buttonText
            )
        }
    }
}

