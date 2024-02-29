package com.example.university.ui.log_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.university.R


@Composable
fun LogInScreen(){

    var text by remember { mutableStateOf("") }
    var text1 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Image(
            // Provide the drawable resource ID using painterResource
            painter = painterResource(id = R.drawable.kpi_logo),
            // Optionally, set content description for accessibility
            contentDescription = "My Drawable"
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextBox(label = "Username")

        TextBox( label = "Password")

    }

}

@Composable
fun TextBox( label: String ){

    var text by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        label =  { Text(label) } ,
        onValueChange = {
            text = it
        },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password)
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LogInScreen()
}