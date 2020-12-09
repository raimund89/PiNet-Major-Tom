package pinet.majortom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import pinet.majortom.ui.MajorTomTheme
import pinet.majortom.ui.components.MajorTomTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MajorTomApp()
        }
    }
}

@Composable
fun MajorTomApp() {
    MajorTomTheme {
        val allScreens = MajorTomScreen.values().toList()
        var currentScreen by savedInstanceState{ MajorTomScreen.Sensors }

        Scaffold(
            topBar = {
                MajorTomTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { screen -> currentScreen = screen },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                currentScreen.content(onScreenChange = { screen -> currentScreen = screen })
            }
        }
    }
}
