package com.lws.admin.lwsadmin.model

import com.lws.admin.lwsadmin.model.response.AccountResponse
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Account")
data class Account(
    @Id
    var id: String = ObjectId().toHexString(),
    var userEmail: String,
    var userPassword: String,
    var userAccessTokens: List<AccessToken>,
    var accountRoles: HashSet<Int>
) {
    fun toAccountResponse(): AccountResponse = AccountResponse(
        id = this.id,
        userEmail = this.userEmail,
        accountRoles = accountRoles.map {
            AccountRole.values()[it]
        }.toHashSet()
    )
}

enum class AccountRole {
    Admin, User
}