package com.madhan.feature_tinder.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_tinder.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
    ) {
        if(icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
            )
        }
        if(icon != null && text != null) {
            Spacer(modifier = Modifier.width(16.dp))
        }
        if(text != null) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomButton(
            text = "Take a picture",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp),
            icon = R.drawable.ic_picture
        )
    }
}
