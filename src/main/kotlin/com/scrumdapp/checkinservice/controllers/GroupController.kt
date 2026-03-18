package com.scrumdapp.checkinservice.controllers

import com.scrumdapp.checkinservice.entities.Group
import org.springframework.http.ResponseEntity
import com.scrumdapp.checkinservice.services.GroupService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import com.scrumdapp.checkinservice.entities.CheckIn
import com.scrumdapp.checkinservice.dto.CheckInDto
import com.scrumdapp.checkinservice.services.CheckInService;

@RestController
@RequestMapping("/groups")
class GroupController(
    private val groupService: GroupService,
    private val checkInService: CheckInService
) {

    @GetMapping
    fun getAllGroups(): ResponseEntity<List<Group>> {
        return ResponseEntity.ok(groupService.getAllGroups())
    }

    @GetMapping("/{id}")
    fun getGroup(@PathVariable id: Int): ResponseEntity<Group> {
        val group = groupService.getGroupById(id)
        println("Group: ${group?.id}")
        return ResponseEntity.ok(group);
    }

    @GetMapping("/{id}/checkins")
    fun getGroupCheckIns(@PathVariable id: Int): List<CheckIn> {
        val group = groupService.getGroupById(id)
        val checkIns: List<CheckIn> = checkInService.findByGroupId(group?.id.toString())
        return checkIns
    }


    @PostMapping
    fun createGroup(@RequestBody group: Group): ResponseEntity<Group> {
        println("Group: ${group.name} + Features: ${group.features}")
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(group))
    }

    @PatchMapping
    fun updateGroup(@RequestBody group: Group): ResponseEntity<Group> {
        return ResponseEntity.ok(groupService.updateGroup(group))
    }

    @DeleteMapping
    fun deleteGroup(@RequestBody id: Int): ResponseEntity<Group> {
        groupService.deleteGroup(id)
        return ResponseEntity.ok().build()
    }
}