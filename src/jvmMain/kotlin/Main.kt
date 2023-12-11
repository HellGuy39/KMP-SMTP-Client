import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.apache.log4j.BasicConfigurator

@Composable
@Preview
fun App() {
    MaterialTheme {
        CreateEmailRoute()
    }
}

fun main() = application {

    // Configure slf4j logger
    BasicConfigurator.configure()

    LaunchedEffect(Unit) {
        MailClient.init()
    }

    Window(
        title = "SMTP Client by HellGuy39",
        onCloseRequest = {
            MailClient.close()
            exitApplication()
        }
    ) {
        App()
    }
}
