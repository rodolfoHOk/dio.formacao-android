package me.dio.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.dio.android.compose.ui.theme.ComposeTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListScreen()
                }
            }
        }
    }
}

data class Person(
    val name: String,
)

@Composable
private fun ListScreen() {
    val persons = listOf(
        Person("Ezequiel"),
        Person("Igor"),
        Person("Pedro"),
        Person("Venilton")
    )

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Yellow)
    ) {
        items(persons) { item ->
            ItemList(name = item.name)
        }
    }

//    LazyRow {
//        items(persons) { item ->
//            ItemList(name = item.name)
//        }
//    }
}

@Composable
private fun ItemList(name: String) {
    Text(text = name, modifier = Modifier.background(Color.Green))
}
