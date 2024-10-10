package com.bruno13palhano.data.model

import com.bruno13palhano.model.Messages

data class MessagesInternal(
    val id: Long,
    val senderUid: String,
    val receiverUid: String,
    val senderName: String,
    val receiverName: String,
    val message: String,
    val timestamp: Long
)

internal fun MessagesInternal.asExternal() = Messages(
    id = id,
    senderUid = senderUid,
    receiverUid = receiverUid,
    senderName = senderName,
    receiverName = receiverName,
    message = message,
    timestamp = timestamp
)

internal fun Messages.asInternal() = MessagesInternal(
    id = id,
    senderUid = senderUid,
    receiverUid = receiverUid,
    senderName = senderName,
    receiverName = receiverName,
    message = message,
    timestamp = timestamp
)