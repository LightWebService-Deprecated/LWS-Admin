package com.lws.admin.lwsadmin.controller.view

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {
    @GetMapping
    fun index(): String {
        return "index"
    }
}