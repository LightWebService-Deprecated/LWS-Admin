package com.lws.admin.lwsadmin.controller

import com.lws.admin.lwsadmin.model.response.AccountResponse
import com.lws.admin.lwsadmin.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val accountService: AccountService
) {
    @GetMapping
    fun listAccounts(): ResponseEntity<List<AccountResponse>> {
        return ResponseEntity.ok(accountService.listAllAccount())
    }

    @DeleteMapping
    fun removeAccount(@RequestParam("id") id: String): ResponseEntity<Any> {
        runCatching {
            accountService.removeAccount(id)
        }.onFailure {
            return ResponseEntity.internalServerError()
                .body(it.message)
        }

        return ResponseEntity.ok().build()
    }

    @PostMapping("/promote")
    fun promoteAdmin(@RequestParam("id") id: String): ResponseEntity<Any> {
        runCatching {
            accountService.promoteAdmin(id)
        }.onFailure {
            return ResponseEntity.internalServerError()
                .body(it.message)
        }

        return ResponseEntity.ok().build()
    }

    @PostMapping("/demote")
    fun demoteAdmin(@RequestParam("id") id: String): ResponseEntity<Any> {
        runCatching {
            accountService.demoteAdmin(id)
        }.onFailure {
            return ResponseEntity.internalServerError()
                .body(it.message)
        }

        return ResponseEntity.ok().build()
    }
}