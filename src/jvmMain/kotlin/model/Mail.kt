package model

data class Mail(
    val from: String,
    val to: String,
    val subject: String,
    val body: String,
)