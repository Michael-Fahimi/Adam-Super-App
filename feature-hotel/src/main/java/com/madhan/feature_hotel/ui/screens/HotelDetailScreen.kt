package com.madhan.feature_hotel.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.madhan.adamsuperapp.ui.theme.PrimaryColor
import com.madhan.adamsuperapp.ui.theme.SecondaryColor
import com.madhan.adamsuperapp.ui.theme.descriptionColor
import com.madhan.adamsuperapp.ui.theme.dividerColor
import com.madhan.adamsuperapp.ui.theme.hotelTextColor
import com.madhan.core.ui.components.PrimaryButton
import com.madhan.feature_hotel.R
import com.madhan.feature_hotel.data.DummyData
import com.madhan.feature_hotel.ui.widgets.CustomTitleText
import com.madhan.feature_hotel.ui.widgets.IconTextRow
import com.madhan.feature_hotel.ui.widgets.RoomInfoRow
import com.madhan.feature_hotel.utils.routes.HOMESCREEN
import com.madhan.feature_hotel.utils.routes.HOTELDETAILSCREEN
import com.madhan.feature_hotel.utils.routes.ORDERSCREEN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailScreen(navController: NavController){
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
        ) {
            //LazyRow moving Images
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp) // Adds spacing between images
            ) {
                items(DummyData.imageList) {  imageResId ->
                    Box(modifier = Modifier) {
                        //images
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = "Hotel Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(215.dp)
                                .width(328.dp)
                                .clip(
                                    RoundedCornerShape(8.dp)

                                )
                        )
                        //Back button
                        IconButton(
                            onClick = { navController.popBackStack(HOMESCREEN, inclusive = false) },
                            modifier = Modifier
                                .padding(16.dp)
                                .size(40.dp)
                                .background(Color.Black.copy(alpha = 0.5f), CircleShape) // Semi-transparent background
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            Surface (modifier =
                Modifier,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .height(130.dp)
                ) {
                    CustomTitleText(
                        modifier = Modifier.absolutePadding(top = 10.dp, left = 15.dp),
                        text = "Ressort Hotel",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start
                    )
                    CustomTitleText(
                        modifier = Modifier.absolutePadding(top = 10.dp, left = 15.dp),
                        text = "Johannesburg",
                        fontWeight = FontWeight.Medium,
                        color = descriptionColor,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(Modifier.height(10.dp))
                    HorizontalDivider(thickness = 0.4.dp, color = dividerColor)
                    Column(modifier = Modifier,
                      horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            IconTextRow(
                                icon = painterResource(id = R.drawable.rate),
                                text = "4.8"
                            )
                            IconTextRow(
                                icon = painterResource(id = R.drawable.nav),
                                text = "500m to center"
                            )
                            IconTextRow(
                                icon = painterResource(id = R.drawable.moon),
                                text = "$ 150"
                            )
                        }
                    }
                }
            }
            //Row of buttons
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PrimaryButton(
                    modifier = Modifier,
                    onClick = {/*Open the call*/},
                    width = 162.dp,
                    buttonBorder = BorderStroke(0.5.dp, descriptionColor),
                    height = 42.dp,
                    colors = ButtonDefaults.buttonColors(Color.White),
                    text="Call",
                    textColor = hotelTextColor
                )
                PrimaryButton(
                    modifier = Modifier,
                    onClick = {/*Open the message*/},
                    width = 162.dp,
                    buttonBorder = BorderStroke(0.5.dp, descriptionColor),
                    height = 42.dp,
                    colors = ButtonDefaults.buttonColors(SecondaryColor),
                    text="Message"
                )

            }

            Spacer(Modifier.height(20.dp))
           //Column
           Column{
               Row(modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
                   horizontalArrangement = Arrangement.SpaceBetween, // Ensures space between texts
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   CustomTitleText(
                      modifier = Modifier.weight(0.3f),
                       text = "Rooms",
                       fontWeight = FontWeight.SemiBold,
                       fontSize = 18.sp,
                       textAlign = TextAlign.Start
                   )
                   CustomTitleText(
                       modifier = Modifier.weight(1f),
                       text = "2",
                       color = descriptionColor,
                       fontWeight = FontWeight.SemiBold,
                       fontSize = 18.sp,
                       textAlign = TextAlign.Start
                   )
               }
           }
            //LazyRow of images
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp) // Adds spacing between images
            ) {
                items(DummyData.hotelRoomList) {  imageId -> // Adjust the number based on your data
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Hotel Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(136.dp)
                            .width(375.dp)
                            .clip(
                                RoundedCornerShape(4.dp)

                            )
                    )
                }
            }

            Spacer(Modifier.height(15.dp))
            //Column
            Column(modifier = Modifier.absolutePadding(top = 10.dp, left = 15.dp)) {
                //Text
                CustomTitleText(
                    text = "Junior Suite",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)) {
                    Column ( modifier = Modifier.weight(1f)){
                        CustomTitleText(
                           // modifier = Modifier.weight(1f),
                            text = "Room",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            color = PrimaryColor
                        )
                        Spacer(Modifier.height(5.dp))
                        CustomTitleText(
                            // modifier = Modifier.weight(1f),
                            text = "1",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = hotelTextColor
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Column ( modifier = Modifier.weight(1f)){
                        CustomTitleText(
                            text = "Adults",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            color = PrimaryColor
                        )
                        Spacer(Modifier.height(5.dp))
                        CustomTitleText(
                            text = "2",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = hotelTextColor
                        )
                    }
                    Column ( modifier = Modifier.weight(1f)){
                        CustomTitleText(
                            text = "Kids",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            color = PrimaryColor
                        )
                        Spacer(Modifier.height(5.dp))
                        CustomTitleText(
                            text = "1",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            color = hotelTextColor
                        )
                    }
                }
            }
            Spacer(Modifier.height(15.dp))
            //Divider
            HorizontalDivider(thickness = 0.4.dp, color = dividerColor)
            Spacer(Modifier.height(15.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                //verticalAlignment = Alignment.,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row{
                    IconTextRow(
                        modifier = Modifier.weight(1f),
                        icon= painterResource(id=R.drawable.wifi),
                        iconSize = 20.dp,
                        text = "Wifi",
                        textColor = descriptionColor,
                        textSize = 14.sp,
                        iconTint = descriptionColor
                    )
                    //Spacer(Modifier.width(10.dp))
                    IconTextRow(
                        modifier = Modifier.weight(1f),
                        icon= painterResource(id=R.drawable.bed),
                        iconSize = 20.dp,
                        text = "Kingsize",
                        textColor = descriptionColor,
                        iconTint = descriptionColor,
                        textSize = 14.sp
                    )
                    Spacer(Modifier.width(10.dp))
                    IconTextRow(
                        modifier = Modifier.weight(1f),
                        icon= painterResource(id=R.drawable.dimension),
                        text = "12 m3",
                        iconSize = 20.dp,
                        textColor = descriptionColor,
                        iconTint = descriptionColor,
                        textSize = 14.sp
                    )
                }
            }
            //Divider
            HorizontalDivider(thickness = 0.6.dp, color = dividerColor)
            Spacer(Modifier.height(10.dp))
            //Row
            Column(modifier = Modifier.absolutePadding(top = 10.dp, left = 15.dp)) {
                RoomInfoRow(
                    title = "Standard",
                    stayIcon = painterResource(id = R.drawable.moon),
                    price = "$150",
                    modalIcon = painterResource(id = R.drawable.plus),
                    onModalClick = { showSheet = true }
                )
                Spacer(Modifier.height(15.dp))
                //Divider
                HorizontalDivider(thickness = 0.4.dp, color = descriptionColor)
                RoomInfoRow(
                    title = "Free Cancellation",
                    stayIcon = painterResource(id = R.drawable.moon),
                    price = "$170",
                    modalIcon = painterResource(id = R.drawable.plus),
                    onModalClick = { showSheet = true }
                )
                Spacer(Modifier.height(15.dp))
                //Divider
                HorizontalDivider(thickness = 0.4.dp, color = descriptionColor)
                RoomInfoRow(
                    title = "Free Cancellation \n Breakfast",
                    stayIcon = painterResource(id = R.drawable.moon),
                    price = "$190",
                    modalIcon = painterResource(id = R.drawable.plus),
                    onModalClick = { showSheet = true }
                )
            }
            //BottomSheet
            Column {
                if (showSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showSheet = false },
                        sheetState = sheetState
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp) // Adjust for half-screen size
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Close button
                            IconButton(
                                onClick = { showSheet = false },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .size(24.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close),
                                    contentDescription = "Close Modal",
                                    tint = PrimaryColor
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            // Modal content
                            Column (
                              horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                //Row
                                Row(
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .padding(bottom = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    CustomTitleText(
                                        modifier = Modifier.weight(1f),
                                        text = "Standard",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Start
                                    )
                                    IconTextRow(
                                        modifier = Modifier.weight(1f),
                                        icon= painterResource(id=R.drawable.moon),
                                        iconSize = 17.dp,
                                        text = "$ 150",
                                        textSize = 14.sp,
                                        iconTint = descriptionColor
                                    )

                                }
                                //Description
                                CustomTitleText(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(bottom = 10.dp),
                                    text = DummyData.hotelDescription,
                                    color = descriptionColor,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start
                                )
                                Spacer(Modifier.height(16.dp))
                                Column(
                                    modifier = Modifier,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Surface(
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .height(51.dp)
                                            .width(51.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(1.dp,descriptionColor)
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center, // Centers the text inside Surface
                                            modifier = Modifier.fillMaxSize() // Ensures Box takes full Surface size
                                        ) {
                                            CustomTitleText(
                                                text = "2",
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 24.sp,
                                                color = hotelTextColor
                                            )
                                        }
                                    }
                                }
                                //Remove text button
                                TextButton(onClick = {}) {
                                    CustomTitleText(
                                        text = "Remove",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                        color = PrimaryColor
                                    )
                                }
                                //Spacer
                               Spacer(Modifier.height(10.dp))
                                PrimaryButton(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    onClick = {
                                        navController.navigate(ORDERSCREEN){
                                            popUpTo(HOTELDETAILSCREEN){inclusive=true}
                                        }
                                              },
                                    text = "Add to Order",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HotelDetailScreenPreview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Enables scrolling
    ) {
    HotelDetailScreen(navController = rememberNavController())
}}