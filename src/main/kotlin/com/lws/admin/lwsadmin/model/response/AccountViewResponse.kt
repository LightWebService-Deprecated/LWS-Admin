package com.lws.admin.lwsadmin.model.response

import com.lws.admin.lwsadmin.model.AccountRole

data class AccountViewResponse(
    val id: String,
    val userEmail: String,
    val accountRoles: HashSet<AccountRole>,
    val isAdmin: Boolean = accountRoles.contains(AccountRole.Admin)
)