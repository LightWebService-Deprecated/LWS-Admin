package com.lws.admin.lwsadmin.controller.view

import com.lws.admin.lwsadmin.model.response.AccountViewResponse
import com.lws.admin.lwsadmin.service.AccountService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class AccountViewController(
    private val accountService: AccountService
) {

    @GetMapping("account")
    fun index(model: Model): String {
        val accountList = accountService.listAllAccount()
            .map {
                AccountViewResponse(
                    id = it.id,
                    userEmail = it.userEmail,
                    accountRoles = it.accountRoles
                )
            }
        model.addAttribute("account_list", accountList)
        return "account-management"
    }
}