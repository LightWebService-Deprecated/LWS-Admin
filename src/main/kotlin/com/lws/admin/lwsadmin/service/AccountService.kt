package com.lws.admin.lwsadmin.service

import com.lws.admin.lwsadmin.configuration.LWSConfiguration
import com.lws.admin.lwsadmin.model.AccountRole
import com.lws.admin.lwsadmin.model.response.AccountResponse
import com.lws.admin.lwsadmin.repository.AccountRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val lwsConfiguration: LWSConfiguration
) {
    private val restTemplate: RestTemplate = RestTemplate()
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun listAllAccount(): List<AccountResponse> {
        return accountRepository.listAllRegistered()
            .map { it.toAccountResponse() }
    }

    fun removeAccount(accountId: String) {
        val deletedCount = accountRepository.removeAccount(accountId)
            .deletedCount

        if (deletedCount != 1L)
            throw IllegalStateException("Deleted Count is not 1!(Got: $deletedCount, accountId: $accountId)")

        // Call to kubernetes server
        val requestAddress = "${lwsConfiguration.gatewayAddress}/api/admin/cluster?userId=$accountId"
        val responseEntity: ResponseEntity<String> = restTemplate.exchange(requestAddress, HttpMethod.DELETE, null)
        if (responseEntity.statusCode != HttpStatus.OK) {
            throw IllegalStateException("Removing user succeeded but cannot remove cluster namespace/Message:${responseEntity.body}")
        }
    }

    fun promoteAdmin(accountId: String) {
        // find account
        val account = accountRepository.getAccountById(accountId)
            ?: throw NullPointerException("Cannot find account with $accountId!")

        // Update Account Role
        account.accountRoles.add(AccountRole.Admin.ordinal)

        // Update Object
        accountRepository.updateAccount(account)
    }

    fun demoteAdmin(accountId: String) {
        // find account
        val account = accountRepository.getAccountById(accountId)
            ?: throw NullPointerException("Cannot find account with $accountId!")

        // Update Account Role
        account.accountRoles.remove(AccountRole.Admin.ordinal)

        accountRepository.updateAccount(account)
    }
}