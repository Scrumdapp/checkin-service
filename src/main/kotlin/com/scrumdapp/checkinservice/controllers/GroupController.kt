package com.scrumdapp.checkinservice.controllers

import com.scrumdapp.checkinservice.entities.Group
import org.springframework.http.ResponseEntity
import com.scrumdapp.checkinservice.repositories.GroupRepository
import org.springframework.web.bind.annotation.*
import java.util.Objects

@RestController
@RequestMapping("/groups")
class GroupController(
    private val groupRepository: GroupRepository
) {

    @GetMapping
    fun getAllGroups(): ResponseEntity<List<Group>> {
        return ResponseEntity.ok(groupRepository.findAll())
    }

    @GetMapping("/{id}")
    fun getGroup(@PathVariable id: Int): ResponseEntity<Group> {
        var group = groupRepository.findGroupById(id)
        println("Group: ${group?.id}")
        return ResponseEntity.ok(group);
    }

    @PostMapping
    fun createGroup(@RequestBody group: Group): ResponseEntity<Group> {
        var group = groupRepository.save(group);
        return ResponseEntity.ok(group);
    }

    @PatchMapping
    fun updateGroup(@RequestBody group: Group): ResponseEntity<Group> {
        if (group.id == null) {
            return ResponseEntity.notFound().build()
        }
        var dbGroup = groupRepository.findGroupById(group.id!!)

        if (Objects.nonNull(group.name) && "" != group.name) dbGroup?.name = group.name

        groupRepository.save(dbGroup!!)
        return ResponseEntity.ok(dbGroup)
    }
}