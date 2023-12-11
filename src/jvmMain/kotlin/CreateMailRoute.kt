import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CreateEmailRoute() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    fun showSnackbar(message: String) {
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { innerPadding ->
        CreateEmailScreen(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding),
            onSendMainClick = { mail -> MailClient.sendEmail(mail, onError = { message -> showSnackbar(message) }) },
            onShowError = { message -> showSnackbar(message) }
        )
    }
}