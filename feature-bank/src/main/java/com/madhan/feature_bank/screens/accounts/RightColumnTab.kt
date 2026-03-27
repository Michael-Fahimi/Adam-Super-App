import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.feature_bank.screens.dashboard.vertical

@Composable
fun RightColumnTab(
    allAccountsTab: @Composable () -> Unit,
    currentAccountsTab: @Composable () -> Unit,
    savingsAccountTab: @Composable () -> Unit,
    creditAccountsTab: @Composable () -> Unit,

    ) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabItems = listOf("All accounts", "Current accounts", "Saving accounts", "Credit accounts")

    Row(modifier = Modifier.fillMaxSize()) {
        // Main Content Section (Left Side)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTabIndex) {
                0 -> allAccountsTab()
                1 -> currentAccountsTab()
                2 -> savingsAccountTab()
                3 -> creditAccountsTab()
            }
        }

        // Vertical Tab Bar (Right Side)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(tabItems.size) { index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable { selectedTabIndex = index }
                            .padding(8.dp)
                    ) {
                        if (index == selectedTabIndex) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(150.dp)
                                    .background(PrimaryColor)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                        }

                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier
                                    .vertical()
                                    .rotate(-90f)
                                    .padding(4.dp),
                                text = tabItems[index]
                            )
                        }

                    }
                }
            }
        }
    }
}

fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }


