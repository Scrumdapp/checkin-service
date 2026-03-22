package com.scrumdapp.checkinservice.controllers

import com.scrumdapp.checkinservice.dto.CheckinCreateDto
import com.scrumdapp.checkinservice.dto.CheckinResponseDto
import com.scrumdapp.checkinservice.dto.CheckinUpdateDto
import com.scrumdapp.checkinservice.dto.DateRange
import com.scrumdapp.checkinservice.dto.GroupCreateDto
import com.scrumdapp.checkinservice.dto.GroupPatchDto
import com.scrumdapp.checkinservice.dto.GroupResponseDto
import com.scrumdapp.checkinservice.entities.CheckInId
import com.scrumdapp.checkinservice.entities.Group
import com.scrumdapp.checkinservice.services.CheckInService
import com.scrumdapp.checkinservice.services.GroupService
import jakarta.validation.Valid
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/groups")
class GroupController(
    private val groupService: GroupService,
    private val checkInService: CheckInService
) {



    @GetMapping
    fun getAllGroups(): ResponseEntity<List<GroupResponseDto>> {
        return ResponseEntity.ok(groupService.getAllGroups())
    }

    @GetMapping("/{id}")
    fun getGroup(@PathVariable id: Int): ResponseEntity<GroupResponseDto> {
        val group = groupService.getGroupById(id)
        println("Group: ${group?.id}")
        return ResponseEntity.ok(group);
    }

    @GetMapping("/{id}/checkins")
    fun getGroupCheckIns(
        @PathVariable id: Int,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) dateTime: LocalDate?
    ): List<CheckinResponseDto> {
        return checkInService.getByGroup(id, dateTime)
    }

    @GetMapping("/{groupId}/users/{userId}/checkins")
    fun getUserCheckInsBetweenDates(
        @PathVariable groupId: Int,
        @PathVariable userId: Int,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate?
    ): List<CheckinResponseDto> {
        var dateRange: DateRange? = null
        if (startDate != null && endDate != null) {
            dateRange = DateRange(startDate, endDate)
        }
        return checkInService.getByGroupAndUser(groupId, userId, dateRange)
    }

    @PostMapping
    fun createGroup(@Valid @RequestBody group: GroupCreateDto): ResponseEntity<GroupResponseDto> {
        println("Group: ${group.name} + Features: ${group.features}")
        val group = groupService.createGroup(group)
        return ResponseEntity.status(HttpStatus.CREATED).body(group)
    }

    @PatchMapping("/{id}")
    fun updateGroup(
        @PathVariable id: Int,
        @Valid @RequestBody group: GroupPatchDto): ResponseEntity<GroupResponseDto> {
        return ResponseEntity.ok(groupService.updateGroup(id, group))
    }

    // Will patch later (takes in List<CheckinUpdateDto>
    @PatchMapping("/{groupId}/checkins")
    fun updateCheckIn(
        @PathVariable groupId: Int,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
        @RequestBody checkinDto: List<CheckinCreateDto>): ResponseEntity<List<CheckinResponseDto>> {

        checkinDto.forEach { it.date = date }
        return ResponseEntity.ok(checkInService.createBatchCheckin(groupId, checkinDto))
    }

    @PatchMapping("/{groupId}/users/{userId}/checkins")
    fun updateUserCheckIn(
        @PathVariable groupId: Int,
        @PathVariable userId: Int,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
        @RequestBody checkinDto: CheckinUpdateDto): ResponseEntity<CheckinResponseDto> {
        val checkinId = CheckInId(
            userId = userId,
            groupId = groupId,
            date = date,
        )
        return ResponseEntity.ok(checkInService.updateCheckin(checkinId, checkinDto))
    }

    @DeleteMapping
    fun deleteGroup(@RequestBody id: Int): ResponseEntity<Group> {
        groupService.deleteGroup(id)
        return ResponseEntity.ok().build()
    }
}