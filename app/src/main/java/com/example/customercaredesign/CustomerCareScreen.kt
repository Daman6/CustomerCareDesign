package com.example.customercaredesign

import android.content.Context
import android.content.Intent
import android.graphics.drawable.VectorDrawable
import android.net.Uri
import android.webkit.URLUtil
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

var CardItemsListData: MutableList<Model> =
    mutableListOf(
        Model("Call", "+1 (234)  234-234", "8:00AM - 8:00PM from Mon-Sat", Icons.Default.Phone),
        Model("Email", "support@viewlift.com", "", Icons.Default.Email),
        Model("Enquiry", "", "", Icons.Default.Send),
    )

var BottomCardListData: MutableList<BottomCardModel> =
    mutableListOf(
        BottomCardModel("Frequently asked questions"),
        BottomCardModel("Feedback"),
    )

@Composable
fun CustomerCareScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = darkBackgroundColor)
            .padding(16.dp)
    ) {

        Card(
            colors = CardDefaults.cardColors(lightBackgroundColor),
            shape = RectangleShape,
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    bottom = 40.dp,
                    start = 16.dp,
                    end = 16.dp,
                    top = 35.dp
                )
            ) {
                items(CardItemsListData) {
                  CardItems(type = it.type, title = it.title , subTitle = it.subTitle , icon = it.icon)
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(lightBackgroundColor),
            shape = RectangleShape,
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 40.dp)
            ) {
                items(BottomCardListData) {
                    BottomCardUi(it.title)
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CardItems(
    type: String,
    title: String,
    subTitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    if (type.equals("Call")){
        CardItemCallUi(type = type, title = title , subTitle = subTitle , icon = icon )
    } else if (type.equals("Email")){
        CardItemEmailUi(type = type, title = title , icon = icon )
    } else if (type.equals("Enquiry")){
        CardItemEnquiryUi(type = type, icon = icon )
    }else{
        //
    }

}


@Composable
fun CardItemCallUi(
    type: String,
    title: String,
    subTitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                openDialpad(title,context)
            }
            .wrapContentHeight()
            .padding(top = 15.dp)
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = type,
                color = lightGrayColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400
            )
            Icon(imageVector = icon, contentDescription = null, tint = lightGrayColor)
        }
        Spacer(modifier = modifier.height(14.dp))
        Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = modifier.height(6.dp))
        Text(
            text = subTitle,
            color = lightGrayColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400
        )
        Spacer(modifier = modifier.height(10.dp))
        Divider(
            modifier = modifier
                .height(1.dp)
                .fillMaxWidth(), color = darkBackgroundColor
        )
    }
}


@Composable
fun CardItemEmailUi(
    type: String,
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                openEmailClient(title,context)
            }
            .wrapContentHeight()
            .padding(top = 15.dp)
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = type,
                color = lightGrayColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400
            )
            Icon(imageVector = icon, contentDescription = null, tint = lightGrayColor)
        }
        Spacer(modifier = modifier.height(14.dp))
        Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = modifier.height(10.dp))
        Divider(
            modifier = modifier
                .height(1.dp)
                .fillMaxWidth(), color = darkBackgroundColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CardItemEnquiryUi(
    type: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    var messageText by remember { mutableStateOf("") }
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            result.value = uri
        }
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 15.dp)
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = type,
                color = lightGrayColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400
            )
            Icon(imageVector = icon, contentDescription = null, tint = lightGrayColor)
        }
        Spacer(modifier = modifier.height(14.dp))
        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            placeholder = {
                Text(
                    "Type your message",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Start,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .offset(-15.dp),
            textStyle = TextStyle(Color.White),
            maxLines = 3,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedButton(
            modifier = modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(Color.Transparent),
            onClick = {
                if (permissionState.status.isGranted) {
                    launcher.launch(arrayOf("application/pdf"))
                } else {
                    permissionState.launchPermissionRequest()
                }
            },
            shape = RectangleShape,
            border = BorderStroke(1.dp, lightGrayColor)
        ) {
            Text(
                text = "File Attachment",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = modifier
                .height(40.dp)
                .fillMaxWidth(),
            onClick = {},
            colors = ButtonDefaults.buttonColors(buttonColor),
            shape = RectangleShape,
        ) {
            Text(
                text = "Send",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center

            )
        }
    }


    Spacer(modifier = modifier.height(10.dp))
    Divider(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth(), color = darkBackgroundColor
    )

}

@Composable
fun BottomCardUi(title: String, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(buttonColor)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.W700
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.White
        )
    }
    Spacer(modifier = Modifier.height(8.dp))

}



private fun openEmailClient(emailid:String,context: Context) {
    val emailUri = Uri.parse("mailto:$emailid")
    val emailIntent = Intent(Intent.ACTION_SENDTO, emailUri)
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello")
    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email")
    emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(emailIntent)
}


private fun openDialpad(number:String,context: Context) {
    val dialpadUri = Uri.parse("tel:$number")
    val dialpadIntent = Intent(Intent.ACTION_DIAL, dialpadUri)
    dialpadIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(dialpadIntent)
}
