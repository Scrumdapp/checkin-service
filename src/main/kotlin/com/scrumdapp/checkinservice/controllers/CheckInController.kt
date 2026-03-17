package com.scrumdapp.checkinservice.controllers;

import com.scrumdapp.checkinservice.entities.CheckIn
import com.scrumdapp.checkinservice.services.CheckInService;
import org.springframework.http.ResponseEntity;
import com.scrumdapp.checkinservice.repositories.CheckInRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/checkins")
 class CheckInController (
    private val checkInService:CheckInService
) {



}
