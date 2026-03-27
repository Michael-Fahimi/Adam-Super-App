import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import com.madhan.feature_delivery.ui.components.BottomOrangeButton
import com.madhan.feature_delivery.ui.components.CustomBackButton
import com.madhan.feature_delivery.ui.components.MapContent
import com.madhan.feature_delivery.R

@Composable
fun OrderDetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Set background color to white
    ) {
        // Map Section (50% of the screen height)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Occupy 50% of the screen
        ) {
            // Google Map Implementation
            val markerPosition = remember { mutableStateOf(LatLng(33.7756, -84.3963)) }
            MapContent(markerPosition = markerPosition.value)

            CustomBackButton {
                navController.popBackStack() // Navigate back when clicked
            }
        }

        // Content Section (50% of the screen height)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Occupy 50% of the screen
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.delivery_guy),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "John Jones",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "⭐ 4.8", fontSize = 16.sp)
                Text(text = "$ 15/kg", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Location",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "261 4th St NW, Atlanta", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            BottomOrangeButton("Confirm") {
                navController.navigate("order_summary")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailsPreview() {
    OrderDetailsScreen(rememberNavController())
}
