package com.madhan.feature_bank.screens.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.core.ui.components.CustomButton
import com.madhan.feature_bank.R
import com.madhan.feature_bank.components.BackTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(navController: NavController) {

    var transactionName by remember { mutableStateOf<String>("") }
    var amount by remember { mutableStateOf<String>("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackTopBar("Transaction", navController)

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "15th March, 2025",
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        IconsList()

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = transactionName,
            singleLine = true,
            label = {
                Text(
                    text = "Transaction Name"
                )
            },
            onValueChange = { newValue -> transactionName = newValue },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = amount,
            singleLine = true,
            label = {
                Text(
                    text = "Amount"
                )
            },
            onValueChange = { newValue -> amount = newValue },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        CustomButton("Pay") {
            navController.navigate("services") {
                popUpTo("ibank_home") {
                    inclusive = true
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(rememberNavController())
}

@Composable
fun IconsList() {
    val outlineIcons = listOf(
        R.drawable.home,
        R.drawable.food,
        R.drawable.move,
        R.drawable.shop,
        R.drawable.misc
    )

    val fillIcons = listOf(
        R.drawable.home_fill,
        R.drawable.food_fill,
        R.drawable.move_fill,
        R.drawable.shop_fill,
        R.drawable.misc_fill
    )

    val iconNames = listOf(
        "home",
        "food",
        "move",
        "shop",
        "misc"
    )

    var selectedIndex by remember { mutableStateOf(-1) }

    Row {
        outlineIcons.forEachIndexed { index, outlineIcon ->
            val isSelected = selectedIndex == index
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = if (isSelected) fillIcons[index] else outlineIcon),
                    contentDescription = "Icon $index",
                    modifier = Modifier
                        .size(52.dp)
                        .padding(4.dp)
                        .clickable { selectedIndex = index }
                )
                Text(
                    text = iconNames[index],
                    color = Color.Gray
                )
            }
        }
    }
}


