package screen.inbox

import model.Mail
import client.MailClient
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun InboxRoute(
    modifier: Modifier = Modifier,
    onMailSelected: (mail: Mail) -> Unit,
    navigateToCreateMail: () -> Unit
) {
    val mails = MailClient.mails

    Box(modifier) {
        InboxScreen(
            mails = mails,
            onMailClick = { mail -> onMailSelected(mail) },
            onCreateMailClick = { navigateToCreateMail() }
        )
    }
}