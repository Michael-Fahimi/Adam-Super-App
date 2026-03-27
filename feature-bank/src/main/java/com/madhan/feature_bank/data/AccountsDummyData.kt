package com.madhan.feature_bank.data

import com.madhan.feature_bank.R

data class Account(
    var drawableId: Int,
    var accountType: String,
    var accountNumber: String,
    var balance: Double,
    var isActive: Boolean
)

data class BankAccount(
    var bankName: String,
    var accounts: List<Account>
)

object AccountsDummyData{
    var allAccounts = listOf(
        BankAccount("Credit Agricole",
            listOf(
                Account(R.drawable.agricole_bank, "Current Account", "XXXXX 1234", 972.00, true),
                Account(R.drawable.agricole_bank, "PEL", "XXXXX 3567", 345.00, true),
                Account(R.drawable.agricole_bank, "Livret A", "XXXXX 3427", 33.76, true)
            )
        ),BankAccount("HSBC",
            listOf(
                Account(R.drawable.bnp_bank, "Current Account", "XXXXX 3567", -134.00, true)
            )
        ),BankAccount("Societe Generale",
            listOf(
                Account(R.drawable.societe_bank, "Current Account", "XXXXX 1234", 21.50, true),
                Account(R.drawable.societe_bank, "PEL", "XXXXX 3567", 15345.00, false),
                Account(R.drawable.societe_bank, "Livret A", "XXXXX 3427", 75.76, true)
            )
        ),
    )

    var currentAccounts = listOf(
        BankAccount("Credit Agricole",
            listOf(
                Account(R.drawable.agricole_bank, "Current Account", "XXXXX 1234", 972.00, true),
                Account(R.drawable.agricole_bank, "Livret A", "XXXXX 3427", 33.76, true)
            )
        ),BankAccount("HSBC",
            listOf(
                Account(R.drawable.bnp_bank, "Current Account", "XXXXX 3567", -134.00, true)
            )
        ),BankAccount("Societe Generale",
            listOf(
                Account(R.drawable.societe_bank, "PEL", "XXXXX 3567", 15345.00, false),
            )
        ),
    )
}