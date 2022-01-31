package com.lws.admin.lwsadmin

import com.lws.admin.lwsadmin.configuration.LWSConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(LWSConfiguration::class)
@SpringBootApplication
class LwsAdminApplication

fun main(args: Array<String>) {
    runApplication<LwsAdminApplication>(*args)
}
