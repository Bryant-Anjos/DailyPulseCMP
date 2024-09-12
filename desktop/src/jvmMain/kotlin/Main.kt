import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.bryant.dailypulse.di.initKoin
import com.bryant.dailypulse.ui.App

fun main() {
    initKoin()

    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Daily Pulse",
        ) {
            App()
        }
    }
}