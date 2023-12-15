package client

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.aspose.email.*
import kotlinx.coroutines.*
import model.Mail
import java.util.*

private val userEmail = ""
private val userPassword = ""

object MailClient {

    private val scope = CoroutineScope(Dispatchers.IO) + SupervisorJob()

    var mails by mutableStateOf<List<Mail>>(listOf())

    private val pop3Client by lazy {
        Pop3Client().apply {
            host = "pop.gmail.com"
            port = 995
            securityOptions = SecurityOptions.SSLImplicit.toInt()
            username = userEmail
            password = userPassword
            enableLogger = true
        }
    }

    private val smtpClient by lazy {
        SmtpClient("smtp.gmail.com").apply {
            username = userEmail
            password = userPassword
            port = 587
        }
    }

    fun loadMails() {
        Locale.setDefault(Locale("en-us"))
        scope.launch {
            mails = pop3Client.listMessages()
                .map { pop3MessageInfo -> pop3Client.fetchMessage(pop3MessageInfo.uniqueId) }
                .map { message ->
                    Mail(
                        from = message.from.displayName,
                        to = message.to.firstOrNull()?.displayName ?: "",
                        subject = message.subject,
                        body = message.body,
                    )
                }
        }
    }

    fun sendMail(mail: Mail) {
        scope.launch {
            val message = MailMessage()

            message.setFrom(MailAddress(mail.from))
            message.to = MailAddressCollection().apply { addMailAddress(MailAddress(mail.to)) }

            message.subject = mail.subject
            message.priority = MailPriority.High
            message.body = mail.body

            smtpClient.send(message)
        }
    }
}