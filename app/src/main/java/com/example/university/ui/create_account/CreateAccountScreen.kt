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
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import com.example.university.data.login.User


@Composable
fun CreateAccountScreen(
    state: UserState,
    goToLoginScreen: () -> Unit,
    addUser: (String, String, String, String, String, goToLoginScreen: () -> Unit) -> Unit
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

        TextBox(
            label = "email",
            textState = email,
            empty = state is UserState.InputError && state.emptyEmail,
        )
        TextBox(
            label = "password",
            textState = password,
            empty = state is UserState.InputError && state.emptyPassword
        )
        TextBox(
            label = "Ім'я",
            textState = name,
            empty = state is UserState.InputError && state.emptyName
        )
        TextBox(
            label = "Прізвище",
            textState = SecondName,
            empty = state is UserState.InputError && state.emptySecondName
        )
        TextBox(
            label = "Група",
            textState = Group,
            empty = state is UserState.InputError && state.emptyGroup
        )

        Button(onClick =
        {
            addUser(
                email.value,
                password.value,
                name.value,
                SecondName.value,
                Group.value,
                goToLoginScreen
            )

        }) {
            Text("Створити акаунт")
        }
    }
}


@Composable
fun TextBox(
    label: String,
    textState: MutableState<String> = rememberSaveable { mutableStateOf("") },
    empty: Boolean = false
) {

    var text by textState

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        label = { Text(label) },
        onValueChange = {
            text = it
        },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (empty) Color.Red else Color.Black // Set the desired unfocused border color
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        )
    )
}
//
//@Composable
//@Preview
//fun CreateAccountPreview() {
//    CreateAccountScreen(UserState.Initial, {}, { "2", "2", "2", "2", "2" {} })
//}

