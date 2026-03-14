package com.scrumdapp.checkinservice.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckinController {

    @GetMapping("/checkin")
    public fun getCheckin(): String {
        return "checkin"
    }

    @PostMapping("")
    public fun postCheckin(): String {
        return "test"
    }
}