package com.madhan.feature_tinder.screens

import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_tinder.R
import com.madhan.feature_tinder.TinderRoute
import com.madhan.feature_tinder.composable.CustomButton
import com.madhan.feature_tinder.composable.HeroImage
import com.madhan.feature_tinder.composable.TitleText
import com.madhan.feature_tinder.composable.TopBar

@Composable
fun TutorialScreen05(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    Column(modifier = modifier) {
        TopBar(
            leftSlot = {
                Icon(
                    painter = painterResource(R.drawable.ic_return),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            navController!!.navigate(TinderRoute.EnableLocation.route)  {
                                popUpTo(TinderRoute.TutorialScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                        .scale(2f)
                        .size(32.dp),
                    tint = PrimaryColor
                )
            }
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeroImage(drawable = R.drawable.img_tinder_screen_05)
                    TitleText(
                        text = "Tutorial"
                    )
                }
                IconRow(
                    icon = R.drawable.ic_rewind,
                    fullText = "Return to profile",
                    boldText = listOf("Return"),
                    iconColor = Color(0xFF2B7FFF)
                )
                IconRow(
                    icon = R.drawable.ic_bigclose,
                    fullText = "No favorite. The profile will not appear.",
                    boldText = listOf("No favorite."),
                    iconColor = Color(0xFFEE7308)
                )
                IconRow(
                    icon = R.drawable.ic_heart,
                    fullText = "Like. If it's mutual, you can talk together (5 per day)",
                    boldText = listOf("Like.", "(5 per day)"),
                    iconColor = Color(0xFF4BEAC5)
                )
                IconRow(
                    icon = R.drawable.ic_star,
                    fullText = "Super like. indicate visually that you are interested (1 per day)",
                    boldText = listOf("Super like", "(1 per day)"),
                    iconColor = Color(0xFFFFCB0E)
                )
                IconRow(
                    icon = R.drawable.ic_fire,
                    fullText = "Boost. Be top profile during 30 minutes.",
                    boldText = listOf("Boost."),
                    iconColor = Color(0xFF5D52DC)
                )
            }

            CustomButton(
                text = "Discover the profiles",
                onClick = {
                    navController!!.navigate(TinderRoute.TinderDisplayScreen.route)  {
                        popUpTo(TinderRoute.TinderScreen.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .heightIn(64.dp)
            )
        }
    }
}


@Composable
fun IconRow(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    fullText: String,
    boldText: List<String>,
    iconColor: Color
) {
    val annotatedString = getAnnotatedString(fullText, boldText)
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
            tint = iconColor,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = annotatedString)
    }
}

fun getAnnotatedString(fullText: String, boldText: List<String>): AnnotatedString {
    return AnnotatedString.Builder().apply {
        append(fullText)
        for(text in boldText) {
            val startIndex = fullText.indexOf(text)
            val endIndex = startIndex + text.length
            addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
                start = startIndex,
                end = endIndex
            )
        }
    }.toAnnotatedString()
}

@Preview(showBackground = true)
@Composable
fun TutorialScreen05Review() {
//    TutorialScreen05()
}