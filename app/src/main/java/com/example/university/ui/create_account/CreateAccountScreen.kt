package com.example.university.ui.create_account

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.university.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.university.data.login.User


@Composable
fun CreateAccountScreen(
    goToLoginScreen: () -> Unit,
    addUser: (User) -> Unit
) {

    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val name = rememberSaveable { mutableStateOf("") }
    val SecondName = rememberSaveable { mutableStateOf("") }
    val Group = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.frog_flower),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )

        TextBox(label = "email", email)
        TextBox(label = "password", password)
        TextBox(label = "Ім'я", name)
        TextBox(label = "Прізвище", SecondName)
        TextBox(label = "Група", Group)

        Button(onClick = {
            addUser(
                User(
                    email = email.value,
                    password = password.value,
                    name = name.value,
                    secondName = SecondName.value,
                    group = Group.value
                )
            )
        }) {
            Text("Створити акаунт")


        }
    }
}


@Composable
fun TextBox(
    label: String,
    textState: MutableState<String> = rememberSaveable { mutableStateOf("") }
) {

    var text by textState

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        label = { Text(label) },
        onValueChange = {
            text = it
        },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        )
    )
}


@Composable
@Preview
fun CreateAccountPreview() {
    CreateAccountScreen({}, {})
}

