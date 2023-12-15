package screen.maildetail

import model.Mail
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MailDetailRoute(
    modifier: Modifier = Modifier,
    mail: Mail?
) {
    Box(modifier) {
        MailDetailScreen(
            mail = mail
        )
    }
}