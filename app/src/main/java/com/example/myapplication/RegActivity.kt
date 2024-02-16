@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegScreen()
        }
    }
}

@Preview
@Composable
fun RegScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val context = LocalContext.current

    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Вход",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 200.dp)
            )
            TextField(
                value = firstName,
                onValueChange = {firstName = it},
                label = { Text("Имя")},
                singleLine = true,
                isError = firstName.isNotEmpty() && !isValidName(firstName),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {})
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = lastName,
                onValueChange = {lastName = it},
                label = { Text("Фамилия")},
                singleLine = true,
                isError = lastName.isNotEmpty() && !isValidName(lastName),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {})
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = phoneNumber,
                onValueChange = {phoneNumber = it},
                label = {Text("Номер телефона")},
                singleLine = true,
                isError = phoneNumber.isNotEmpty() && !isValidPhoneNumber(phoneNumber),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {})
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val intent = Intent(context, CatalActivity::class.java)
                    context.startActivity(intent)
                },
                enabled = isValidName(firstName) && isValidName(lastName) && isValidPhoneNumber(phoneNumber)
            ) {
                Text("Войти")
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Нажимая кнопку \"Войти\", Вы принимаете условия программы лояльности.",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }

}
private fun isValidName(name: String): Boolean {
    return name.all { it.isLetter() }
}

private fun isValidPhoneNumber(phoneNumber: String): Boolean {
    val phoneNumberRegex = Regex("""^\+7 \d{3} \d{3}-\d{2}-\d{2}$""")
    return phoneNumber.matches(phoneNumberRegex)
}