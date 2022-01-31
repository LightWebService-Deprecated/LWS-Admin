package com.lws.admin.lwsadmin.model

data class AccessToken(
    var createdAt: Long,
    var expiresAt: Long,
    var token: String,
    var isExpiredExternally: Boolean
)