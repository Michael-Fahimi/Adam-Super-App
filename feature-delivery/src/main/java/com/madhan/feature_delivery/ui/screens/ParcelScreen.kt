package com.madhan.feature_delivery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.feature_delivery.ui.components.BottomOrangeButton
import com.madhan.feature_delivery.ui.components.HomeIcon


@Composable
fun ParcelScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopCenter
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with Home Icon and Title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeIcon(onClick = { navController.navigate("home") })
                //Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Your parcel",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sliders
            ParcelSlider(label = "Weight/kg", range = 0f..10f, initial = 5f)
            ParcelSlider(label = "Height/cm", range = 0f..100f, initial = 50f)
            ParcelSlider(label = "Width/cm", range = 0f..100f, initial = 50f)
            ParcelSlider(label = "Depth/cm", range = 0f..100f, initial = 50f)

            Spacer(modifier = Modifier.height(16.dp))

            // Brittle Checkbox Row
            BrittleCheckboxRow()

            Spacer(modifier = Modifier.height(80.dp))

            // Next Button
            BottomOrangeButton(text = "Next") {
                navController.navigate("delivery_address")

            }
        }
    }
}

@Composable
fun BrittleCheckboxRow() {
    var isBrittle by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Brittle", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isBrittle,
                onCheckedChange = { isBrittle = true },
                colors = CheckboxDefaults.colors(Color(0xFFFF8000))
            )
            Text("Yes")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = !isBrittle,
                onCheckedChange = { isBrittle = false },
                colors = CheckboxDefaults.colors(Color.Gray)
            )
            Text("No")
        }
    }
}

@Composable
fun ParcelSlider(label: String, range: ClosedFloatingPointRange<Float>, initial: Float) {
    var value by remember { mutableFloatStateOf(initial) }
    var displayedValue by remember { mutableFloatStateOf(initial) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(label, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(range.start.toInt().toString(), fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(displayedValue.toInt().toString(), fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(range.endInclusive.toInt().toString(), fontSize = 16.sp, color = Color(0xFFDDDDDD))
        }
        Slider(
            value = value,
            onValueChange = {
                value = it
                displayedValue = it
            },
            valueRange = range,
            colors = SliderDefaults.colors(
                thumbColor = Color.Transparent,
                activeTrackColor = Color(0xFFFF8000),
                inactiveTrackColor = Color(0xFFDDDDDD)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()
                    val outerRadius = 8.dp.toPx()
                    val innerRadius = 4.dp.toPx()
                    val centerX = size.width * (value - range.start) / (range.endInclusive - range.start)
                    val centerY = size.height / 2

                    drawCircle(
                        color = Color((0xFFFF8000)),
                        radius = outerRadius,
                        center = Offset(centerX, centerY)
                    )
                    drawCircle(
                        color = Color.White,
                        radius = innerRadius,
                    )
                }
                .pointerInput(Unit) { // Use pointerInput modifier
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val change = event.changes.first()
                            if (change.pressed) {
                                val offset = change.position
                                val clickedValue = range.start + (offset.x / size.width) * (range.endInclusive - range.start)
                                value = clickedValue.coerceIn(range.start, range.endInclusive)
                                displayedValue = value
                            }
                        }
                    }
                }
        )
    }
}


    @Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    ParcelScreen(navController = navController)
}