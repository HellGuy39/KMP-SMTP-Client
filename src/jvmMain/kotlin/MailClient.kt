import kotlinx.coroutines.*
import net.axay.simplekotlinmail.delivery.MailerManager
import net.axay.simplekotlinmail.delivery.mailerBuilder
import net.axay.simplekotlinmail.delivery.send
import net.axay.simplekotlinmail.email.emailBuilder

object MailClient {

    private val scope = CoroutineScope(Dispatchers.IO) + SupervisorJob()

    private val mailer by lazy {
        mailerBuilder(host = "localhost", port = 25)
    }

    fun init() {
        MailerManager.defaultMailer = mailer
    }

    fun sendEmail(
        mail: Mail,
        onError: (message: String) -> Unit = {}
    ) {
        val email = emailBuilder {
            from(mail.from)
            to(mail.to)

            withSubject(mail.subject)
            withPlainText(mail.body)
        }

        scope.launch {
            runCatching { email.send().join() }
                .onFailure { throwable ->
                    throwable.printStackTrace()
                    onError(throwable.message.toString())
                }
        }
    }

    fun close() {
        scope.launch {
            MailerManager.shutdownMailers()
        }
    }
}