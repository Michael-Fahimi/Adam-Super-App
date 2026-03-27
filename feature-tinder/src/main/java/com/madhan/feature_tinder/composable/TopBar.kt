package com.madhan.feature_tinder.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    leftSlot:  @Composable () -> Unit = {},
    middleSlot: @Composable () -> Unit = {},
    rightSlot: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, vertical = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            leftSlot()
        }
        Box(
            modifier = Modifier
                .weight(10f),
            contentAlignment = Alignment.Center
        ) {
            middleSlot()
        }
        Box(
            modifier = Modifier
                .weight(2f),
            contentAlignment = Alignment.Center
        ) {
            rightSlot()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(
        leftSlot = {
            Text(text = "Hello")
        },
        middleSlot = {
            Text(text = "World")
        },
        rightSlot = {
            Text("Sir")
        }
    )
}