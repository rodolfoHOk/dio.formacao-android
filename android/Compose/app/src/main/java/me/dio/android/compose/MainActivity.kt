package me.dio.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.dio.android.compose.ui.theme.ComposeTheme
import me.dio.android.compose.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var clicks by remember {
        mutableStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ClickCounter(clicks = clicks) {
            clicks++
        }

        HelloContent()
    }
}

@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }

        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text("Name")
            }
        )
    }
}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}

data class Dog(
    val name: String,
    val breed: String
)

@Composable
private fun DogCard(dog: Dog, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_dog),
            contentDescription = "Dog image",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
        )

        Column {
            Text(text = dog.name)

            Text(text = dog.breed)
        }
    }
}

@Composable
private fun TestingColumnRow() {
    Column(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        Text(
            text = "Hello World!!!",
            maxLines = 3,
            style = Typography.headlineSmall,
            modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
        )

        Text(
            text = "Outro texto qualquer",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        )

        Row {
            Text(text = "Texto 3", modifier = Modifier.padding(end = 30.dp))

            Text(text = "Texto 4")
        }
    }
}

@Composable
private fun TestingButton() {
    Text(text = "Outro texto qualquer")
    Button(
        onClick = { /* TO DO */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Yellow,
            contentColor = Color.Black
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_award_star),
            contentDescription = "Star",
            tint = Color.Red
        )
        Text(text = "Confirmar", color = Color.Blue)
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Composable
//@Preview(showBackground = true)
//fun GreetingPreview() {
//    ComposeTheme {
//        Greeting("Rudolf")
//    }
//}
