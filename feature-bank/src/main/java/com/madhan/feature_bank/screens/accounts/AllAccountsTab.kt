package com.madhan.feature_bank.screens.accounts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhan.feature_bank.components.AccountTabItem
import com.madhan.feature_bank.data.AccountsDummyData

@Composable
fun AllAccountsTab(){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(4.dp)
    ) {
        AllAccountsList()
    }
}

@Composable
fun AllAccountsList(){

    LazyColumn {
        AccountsDummyData.allAccounts.forEach { bankAccount ->
            item {
                Text(
                    text = bankAccount.bankName,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            items(bankAccount.accounts) { account ->
                AccountTabItem(account)
                Spacer( modifier = Modifier.height(16.dp) )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AllAccountsTabPreview(){
    AllAccountsTab()
}
