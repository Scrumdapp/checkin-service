package com.scrumdapp.checkinservice.services

import com.scrumdapp.checkinservice.entities.Group
import com.scrumdapp.checkinservice.repositories.GroupRepository
import org.springframework.data.repository.support.Repositories
import org.springframework.stereotype.Service


interface GroupService {
    fun getAllGroups(): List<Group>
    fun getGroupById(id: Int): Group?
    fun createGroup(group: Group): Group
    fun updateGroup(group: Group): Group
    fun deleteGroup(id: Int)
}

@Service
class GroupServiceImpl(
    private val groupRepository: GroupRepository
) : GroupService {
    override fun getAllGroups(): List<Group> {
        return groupRepository.findAll()
    }

    override fun getGroupById(id: Int): Group? {
        return groupRepository.findGroupById(id)
    }

    override fun createGroup(group: Group): Group {
        return groupRepository.save(group)
    }

    override fun updateGroup(group: Group): Group {
        return groupRepository.save(group)
    }

    override fun deleteGroup(id: Int) {
        return groupRepository.deleteGroupById(id)
    }

}