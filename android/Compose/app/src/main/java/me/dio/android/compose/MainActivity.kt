package me.dio.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

//    Text(text = "Outro texto qualquer")
//    Button(
//        onClick = { /* TO DO */ },
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Yellow,
//            contentColor = Color.Black
//        )
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.ic_award_star),
//            contentDescription = "Star",
//            tint = Color.Red
//        )
//        Text(text = "Confirmar", color = Color.Blue)
//    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Composable
//@Preview(showBackground = true)
//fun GreetingPreview() {
//    ComposeTheme {
//        Greeting("Rudolf")
//    }
//}
