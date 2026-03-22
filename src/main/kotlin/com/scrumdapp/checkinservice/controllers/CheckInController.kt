package com.scrumdapp.checkinservice.controllers;

import com.scrumdapp.checkinservice.services.CheckInService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkins")
 class CheckInController (
    private val checkInService:CheckInService
) {

}
