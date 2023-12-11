import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateEmailScreen(
    modifier: Modifier,
    onSendMainClick: (mail: Mail) -> Unit,
    onShowError: (message: String) -> Unit
) {

    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    fun clearFields() {
        from = ""
        to = ""
        subject = ""
        body = ""
    }

    fun validate(): Boolean {
        if (from.isEmpty()) {
            onShowError("'From' field should not be empty")
            return false
        }
        if (to.isEmpty()) {
            onShowError("'To' field should not be empty")
            return false
        }
        if (subject.isEmpty()) {
            onShowError("'Subject' field should not be empty")
            return false
        }
        if (body.isEmpty()) {
            onShowError("'Body' field should not be empty")
            return false
        }
        return true
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Email Message",
            style = MaterialTheme.typography.h5
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                value = from,
                onValueChange = { text -> from = text },
                label = {
                    Text("From")
                },
                maxLines = 1
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                value = to,
                onValueChange = { text -> to = text },
                label = {
                    Text("To")
                },
                maxLines = 1
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = subject,
            onValueChange = { text -> subject = text },
            label = {
                Text("Subject")
            },
            maxLines = 1
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = body,
            onValueChange = { text -> body = text },
            label = {
                Text("Body")
            },
            minLines = 5
        )
        Row {
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {

                    if (!validate()) { return@Button }

                    val mail = Mail(
                        from = from,
                        to = to,
                        subject = subject,
                        body = body,
                    )

                    clearFields()
                    onSendMainClick(mail)
                }
            ) {
                Text("Send Mail")
            }
        }
    }
}
