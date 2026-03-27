package com.madhan.feature_tinder.composable

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.dummydata.Profile
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    profile: Profile,
    onDismissedLeft: () -> Unit,
    onDismissedRight: () -> Unit,
    updateColor: (CardBgColor) -> Unit,
    currentColor: CardBgColor,
    nextColor: CardBgColor
) {
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp.dp
    val imageHeight = screenHeight * 0.8f
    // Swipe Animation

    Surface(
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .swipe(
                onDismissedLeft = {
                    onDismissedLeft()
                },
                onDismissedRight = {
                    onDismissedRight()
                },
                updateColor = updateColor,
                nextColor = nextColor,
                currentColor = currentColor
            )
    ) {
        Column(
            modifier = Modifier
                .background(currentColor.color)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(profile.photo),
                contentDescription = "",
                modifier = Modifier
                    .height(imageHeight * 0.9f)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${profile.firstName}, ${profile.age}",
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = profile.photoViews)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_picture),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        }
    }
}


fun Modifier.swipe(
    onDismissedLeft: () -> Unit,
    onDismissedRight: () -> Unit,
    updateColor: (CardBgColor) -> Unit,
    nextColor: CardBgColor,
    currentColor: CardBgColor
): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    if(offsetX.value > 0) {
        updateColor(CardBgColor.REJECTED)
    }
    if(offsetX.value < 0) {
        updateColor(CardBgColor.LIKED)
    }
    pointerInput(Unit) {
        // Used to calculate fling decay.
        val decay = splineBasedDecay<Float>(this)
        // Use suspend functions for touch events and the Animatable.
        coroutineScope {
            while (true) {
                val velocityTracker = VelocityTracker()
                // Stop any ongoing animation.
                offsetX.stop()
                awaitPointerEventScope {
                    // Detect a touch down event.
                    val pointerId = awaitFirstDown().id

                    horizontalDrag(pointerId) { change ->
                        // Update the animation value with touch events.
                        launch {
                            offsetX.snapTo(
                                offsetX.value + change.positionChange().x
                            )
                        }
                        velocityTracker.addPosition(
                            change.uptimeMillis,
                            change.position
                        )
                    }
                }
                // No longer receiving touch events. Prepare the animation.
                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(
                    offsetX.value,
                    velocity
                )
                // The animation stops when it reaches the bounds.
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat() * 100,
                    upperBound = size.width.toFloat() * 100
                )
                launch {
                    if (targetOffsetX.absoluteValue <= size.width) {
                        // Not enough velocity; Slide back.
                        offsetX.animateTo(
                            targetValue = 0f,
                            initialVelocity = velocity
                        )
                        updateColor(currentColor)
                    } else {
                        // The element was swiped away.
                        offsetX.animateDecay(velocity, decay)
                        if(offsetX.value < 0 ) {
                            onDismissedLeft()
                        }
                        if(offsetX.value > 0) {
                            onDismissedRight()
                        }
                        updateColor(nextColor)
                        offsetX.snapTo(0f)
                    }
                }
            }
        }
    }
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}
