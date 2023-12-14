package com.nsteuerberg.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nsteuerberg.simondice.ui.theme.UserInterface
import com.nsteuerberg.simondice.ui.theme.SimonDiceTheme

/**
 * Videogame Simon Says
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataContext.init(applicationContext)
        var myViewModel = MyViewModel()
        setContent {
            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(255,100,150)
                ) {
                    UserInterface(miViewModel = myViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var myViewModel = MyViewModel()
    SimonDiceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(255,100,150)
        ) {
            UserInterface(miViewModel = myViewModel)
        }
    }
}