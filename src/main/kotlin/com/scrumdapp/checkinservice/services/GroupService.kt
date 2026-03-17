package com.scrumdapp.checkinservice.services

import com.scrumdapp.checkinservice.entities.Group
import com.scrumdapp.checkinservice.repositories.GroupFeatureRepository
import com.scrumdapp.checkinservice.repositories.GroupRepository
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat

interface GroupService {
    fun getAllGroups(): List<Group>
    fun getGroupById(id: Int): Group?
    fun addGroupFeatures(id: Int, featureKeys: List<String>): Group?
    fun createGroup(group: Group): Group
    fun updateGroup(group: Group): Group
    fun deleteGroup(id: Int)

}

@Service
class GroupServiceImpl(
    private val groupRepository: GroupRepository,
    private val groupFeatureRepository: GroupFeatureRepository,
) : GroupService {
    override fun getAllGroups(): List<Group> {
        return groupRepository.findAll()
    }

    override fun getGroupById(id: Int): Group? {
        return groupRepository.findGroupById(id)
    }

    override fun addGroupFeatures(id: Int, featureKeys: List<String>): Group? {
        val group = groupRepository.findGroupById(id)?: return null

        val features = groupFeatureRepository.findAllByKeyIn(featureKeys)

        group.features.addAll(features)
        return groupRepository.save(group)
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