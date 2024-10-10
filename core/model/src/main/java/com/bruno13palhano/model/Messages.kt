package com.bruno13palhano.model

data class Messages(
    val id: Long,
    val senderUid: String,
    val receiverUid: String,
    val senderName: String,
    val receiverName: String,
    val message: String,
    val timestamp: Long
)
