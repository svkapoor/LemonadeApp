package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var clickCount by remember { mutableStateOf(1) }
    var currentImage by remember { mutableStateOf( R.drawable.lemon_tree) }
    var instructions by remember {
        mutableStateOf("Tap the lemon tree to select a lemon")
    }
    var num by remember { mutableStateOf(0) }
    var matchingNum by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            when (clickCount) {
                0 -> {
                    clickCount++
                    currentImage = R.drawable.lemon_tree
                    instructions = "Tap the lemon tree to select a lemon"
                }

                1 -> {
                    clickCount++
                    currentImage = R.drawable.lemon_squeeze
                    instructions = "Keep tapping the lemon to squeeze it"
                }

                2 -> {
                    matchingNum++
                    if (num == 0) {
                        num = (2..4).random()
                        num -= 1
                    }
                    if (matchingNum == num) {
                        clickCount++
                        matchingNum = 0
                        num = 0
                    }
                }

                3 -> {
                    clickCount++
                    currentImage = R.drawable.lemon_drink
                    instructions = "Tap the lemonade to drink it"
                }

                4 -> {
                    clickCount = 0
                    currentImage = R.drawable.lemon_restart
                    instructions = "Tap the empty glass to start again"
                }

            }
        }) {
            Image(
                painter = painterResource(currentImage),
                contentDescription = "Image 1",

            )
        }

        Text(
            text = instructions,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Lemonade()
    }
}