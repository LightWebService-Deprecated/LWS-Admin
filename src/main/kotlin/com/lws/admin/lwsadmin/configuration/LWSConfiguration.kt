package com.lws.admin.lwsadmin.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "lws")
class LWSConfiguration(
    val gatewayAddress: String
)