package com.madhan.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.adamsuperapp.ui.theme.SecondaryColor
import com.madhan.adamsuperapp.ui.theme.errorColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class SnackType {
    SUCCESS, ERROR
}

@Composable
fun CustomSnackbar(
    message: String,
    snackType: SnackType,
    modifier: Modifier = Modifier,
    duration: Long = 3000L, // Snackbar auto-dismiss after 3 seconds
    onDismiss: () -> Unit
) {
    val backgroundColor = when (snackType) {
        SnackType.SUCCESS -> SecondaryColor // Green
        SnackType.ERROR -> errorColor  // Red
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(duration)
            onDismiss()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter // Align Snackbar to the bottom
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
