package com.lws.admin.lwsadmin.repository

import com.lws.admin.lwsadmin.model.Account
import com.mongodb.client.result.DeleteResult
import org.bson.Document
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class AccountRepository(
    private val mongoTemplate: MongoTemplate
) {
    // Logger
//    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    // Field Name
    private val idField: String = "_id"

    fun listAllRegistered(): List<Account> {
        return mongoTemplate.findAll()
    }

    fun removeAccount(accountId: String): DeleteResult {
        val query = Query().addCriteria(Criteria.where(idField).`is`(accountId))
        return mongoTemplate.remove(query, Account::class.java)
    }

    fun updateAccount(account: Account) {
        val findQuery = Query().addCriteria(Criteria.where(idField).`is`(account.id))

        // Get Update Query
        val updateQuery = getUpdateQueryFromObject(account)

        mongoTemplate.updateFirst(findQuery, updateQuery, Account::class.java)
    }

    fun getAccountById(accountId: String): Account? {
        return mongoTemplate.findOne(
            Query().addCriteria(Criteria.where(idField).`is`(accountId))
        )
    }

    private fun getUpdateQueryFromObject(account: Account): Update {
        val document = Document()
        mongoTemplate.converter.write(account, document)

        return Update.fromDocument(document)
    }
}