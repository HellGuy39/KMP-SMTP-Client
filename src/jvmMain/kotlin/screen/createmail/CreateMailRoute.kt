package screen.createmail

import client.MailClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateEmailRoute(
    navigateToMain: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        CreateEmailScreen(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding),
            onSendMainClick = { mail ->
                MailClient.sendMail(mail)
                showSnackbar("Your message has been sent")
                navigateToMain()
            },
            onShowError = { message -> showSnackbar(message) },
            onBackClick = { navigateToMain() }
        )
    }
}