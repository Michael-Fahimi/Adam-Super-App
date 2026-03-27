package com.madhan.feature_bank.screens.code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
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
fun CodeScreen(navController: NavController){

    var accountCode by remember { mutableStateOf<String>("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {

        BackTopBar("Code", navController)

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.code_screen_image),
            contentDescription = "login screen image",
            modifier = Modifier.width(305.dp)
                .height(233.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Enter the personal code you use on your bank's website",
            color = Color.Gray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(300.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = accountCode,
            singleLine = true,
            label = {
                Text(
                    text = "Account Code"
                )
            },
            onValueChange = { newValue -> accountCode = newValue },
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
        CustomButton("Connect") {
            navController.navigate("loading")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CodeScreenPreview(){
    CodeScreen(rememberNavController())
}