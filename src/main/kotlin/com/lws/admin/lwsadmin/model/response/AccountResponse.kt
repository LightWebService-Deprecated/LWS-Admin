package com.lws.admin.lwsadmin.model.response

import com.lws.admin.lwsadmin.model.AccountRole

data class AccountResponse(
    val id: String,
    val userEmail: String,
    val accountRoles: HashSet<AccountRole>
)