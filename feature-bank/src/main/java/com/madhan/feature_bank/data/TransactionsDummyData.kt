package com.madhan.feature_bank.data

import com.madhan.feature_bank.R

data class TransactionsData(
    var drawableId: Int,
    var transactionTo: String,
    var bankType: String,
    var amount: String
)


data class TransactionHistory(
    var date: String,
    var transactionsList: List<TransactionsData>
)

object TransactionsDummyData{

    var transactionsList = listOf(
        TransactionHistory(
            date = "20 January, 2018",
            transactionsList = listOf(
                TransactionsData(R.drawable.shop_fill, "CB SARL XXXX", "CA Current", "972")
            )
        ),
        TransactionHistory(
            date = "19 January, 2018",
            transactionsList = listOf(
                TransactionsData(R.drawable.shop_fill, "CB SARL XXXX", "CA Current", "972"),
                TransactionsData(R.drawable.food_fill, "Resttuarent", "HSBC", "235.34"),
                TransactionsData(R.drawable.home_fill, "Doors", "CA Current", "124.21"),
                TransactionsData(R.drawable.misc_fill, "GYM", "HSBC", "39.34")
            )
        ),TransactionHistory(
            date = "18 January, 2018",
            transactionsList = listOf(
                TransactionsData(R.drawable.shop_fill, "CB SARL XXXX", "CA Current", "972"),
                TransactionsData(R.drawable.food_fill, "Resttuarent", "HSBC", "235.34"),
                TransactionsData(R.drawable.home_fill, "Doors", "CA Current", "124.21"),
                TransactionsData(R.drawable.misc_fill, "GYM", "HSBC", "39.34")
            )
        ),

    )
}