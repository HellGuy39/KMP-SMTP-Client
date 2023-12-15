import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import client.MailClient
import kotlinx.coroutines.launch
import model.Mail
import org.apache.log4j.BasicConfigurator
import screen.Route
import screen.createmail.CreateEmailRoute
import screen.inbox.InboxRoute
import screen.maildetail.MailDetailRoute

@Composable
@Preview
fun App() {
    MaterialTheme {

        var selectedMail by remember { mutableStateOf<Mail?>(null) }
        var route by remember { mutableStateOf<Route>(Route.Main) }

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        fun showSnackbar(message: String) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message
                )
            }
        }

        LaunchedEffect(Unit) {
            MailClient.loadMails()
        }

        Scaffold(
            scaffoldState = scaffoldState
        ) {
            when(route) {
                Route.Main -> {
                    Row {
                        InboxRoute(
                            modifier = Modifier.weight(1f),
                            onMailSelected = { mail ->
                                selectedMail = mail
                            },
                            navigateToCreateMail = {
                                route = Route.CreateMail
                            }
                        )

                        MailDetailRoute(
                            modifier = Modifier.weight(1f),
                            mail = selectedMail
                        )
                    }
                }
                Route.CreateMail -> {
                    CreateEmailRoute(
                        navigateToMain = {
                            route = Route.Main
                        },
                        showSnackbar = { showSnackbar(it) }
                    )
                }
            }
        }
    }
}

fun main() = application {

    // Configure slf4j logger
    BasicConfigurator.configure()

    Window(
        title = "model.Mail client by HellGuy39",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
