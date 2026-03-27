package com.madhan.feature_uber.Screens.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.composed

sealed class PaymentMethod {
    data class Card(
        val cardNumber: String,
        val cardHolderName: String,
        val color: Color
    ) : PaymentMethod()

    object Cash : PaymentMethod()
}

@Composable
fun PaymentScreen(
    balance: Double,
    paymentAmount: Double,
    paymentMethods: List<PaymentMethod>,
    onPayClick: (PaymentMethod) -> Unit,
    onHomeClick: () -> Unit
) {
    val orange = Color(0xFFFF7D1E)
    val lightGray = Color(0xFFF5F5F5)
    val goldCard = Color(0xFFDAA520)
    val greenCard = Color(0xFF00C853)

    var selectedPaymentIndex by remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState()

    val selectedPayment by remember(selectedPaymentIndex) {
        derivedStateOf {
            if (selectedPaymentIndex < paymentMethods.size) {
                paymentMethods[selectedPaymentIndex]
            } else {
                null
            }
        }
    }

    val transactionFee = 0.00 // No transaction fee
    val totalAmount = paymentAmount + transactionFee
    val balanceAfterPayment = balance - totalAmount

    Scaffold(
        modifier = Modifier.background(lightGray)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(lightGray)
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = orange,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(onClick = onHomeClick)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Choose your payment",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Balance
            Text(
                text = "Your balance",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Text(
                text = "$ ${String.format("%.2f", balance)}",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Payment methods carousel
            LazyRow(
                state = lazyListState,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(paymentMethods) { index, paymentMethod ->
                    Box(
                        modifier = Modifier.clickable { selectedPaymentIndex = index }
                    ) {
                        when (paymentMethod) {
                            is PaymentMethod.Card -> {
                                CreditCardItem(
                                    cardNumber = paymentMethod.cardNumber,
                                    cardHolderName = paymentMethod.cardHolderName,
                                    color = paymentMethod.color,
                                    isSelected = selectedPaymentIndex == index
                                )
                            }
                            is PaymentMethod.Cash -> {
                                CashPaymentItem(
                                    isSelected = selectedPaymentIndex == index
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Indicator dots
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(paymentMethods.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (selectedPaymentIndex == index) orange else Color.LightGray)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Transaction details
            Text(
                text = "Transaction fees",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Text(
                text = "$ ${String.format("%.2f", transactionFee)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total amount",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Text(
                text = "$ ${String.format("%.2f", totalAmount)}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = orange
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(color = Color.LightGray)

            Spacer(modifier = Modifier.height(16.dp))

            // Balance after payment
            Text(
                text = "Your balance after payment",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Text(
                text = "$ ${String.format("%.2f", balanceAfterPayment)}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            // Pay button
            Button(
                onClick = {
                    selectedPayment?.let { onPayClick(it) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Pay",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CreditCardItem(
    cardNumber: String,
    cardHolderName: String,
    color: Color,
    isSelected: Boolean
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Card logo
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "US",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            Column {
                Text(
                    text = "ACCOUNT NUMBER",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = formatCardNumber(cardNumber),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = cardHolderName,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Composable
fun CashPaymentItem(
    isSelected: Boolean
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF00C853)),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Payment,
                contentDescription = "Cash Payment",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pay with Cash",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun formatCardNumber(cardNumber: String): String {
    return cardNumber.chunked(4).joinToString(" ")
}

// Extension function to add clickable without ripple effect
@SuppressLint("SuspiciousModifierThen")
@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.then(
        clickable(
            onClick = onClick,
            indication = null, // Removes the ripple effect
            interactionSource = remember { MutableInteractionSource() } // Fixes the issue
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    MaterialTheme {
        val paymentMethods = listOf(
            PaymentMethod.Card(
                cardNumber = "777777777777777777777",
                cardHolderName = "Adam Adamian",
                color = Color(0xFFDAA520) // Gold color
            ),
            PaymentMethod.Cash
        )

        PaymentScreen(
            balance = 5523.26,
            paymentAmount = 9.50,
            paymentMethods = paymentMethods,
            onPayClick = {},
            onHomeClick = {}
        )
    }
}