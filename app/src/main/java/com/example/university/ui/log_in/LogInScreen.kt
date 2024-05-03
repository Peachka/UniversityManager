package com.example.university.ui.log_in

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.university.R


@Composable
fun LogInScreen(
    inputState: UserInput,
    goToCreateAccount: () -> Unit,
    validateInput: (String, String) -> Unit
) {

    val username = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.kpi_logo),
            contentDescription = "My Drawable"
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextBox(label = "Email", textState = username, inputError = inputState.emailError)

        TextBox(label = "Password", textState = password, inputError = inputState.passwordError)

        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .fillMaxWidth()
                .clickable {
                    goToCreateAccount()
                },
            text = "Create account",
            textAlign = TextAlign.End

        )
        Button(
            onClick = { validateInput(username.value, password.value)
                      Log.d("Log in screen", "$inputState")},
        ) {
            Text(text = "Увійти")

        }


    }

}

@Composable
fun TextBox(label: String, textState: MutableState<String>, inputError: Boolean) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = textState.value,
        label = { Text(label) },
        onValueChange = {
            textState.value = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (inputError) Color.Red else Color.Black // Set the desired unfocused border color
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        )
    )
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LogInScreen({})
//}