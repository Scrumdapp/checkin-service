package com.scrumdapp.checkinservice.services

import com.scrumdapp.checkinservice.dto.CheckInDto
import com.scrumdapp.checkinservice.entities.CheckInId
import com.scrumdapp.checkinservice.mappers.toDto
import com.scrumdapp.checkinservice.mappers.toEntity
import com.scrumdapp.checkinservice.repositories.CheckInRepository
import com.scrumdapp.checkinservice.repositories.GroupRepository
import org.springframework.stereotype.Service
import java.util.Date
import java.time.LocalDate


interface CheckInService {

    fun findById(id: CheckInId): CheckInDto?

    fun deleteById(id: CheckInId)

    fun saveCheckIn(checkIn: CheckInDto): CheckInDto

    fun findByGroupId(groupId: Int): List<CheckInDto>

    fun findByUserAndDateRange(
        groupId: Int,
        userId: Int,
        start: LocalDate,
        end: LocalDate
    ): List<CheckInDto>

    fun findByGroupIdAndDate(
        groupId: Int,
        date: LocalDate,
    ): List<CheckInDto>

}

@Service
class CheckInServiceImpl(
    private val checkInRepository: CheckInRepository,
    private val groupRepository: GroupRepository
) : CheckInService {

    override fun findById(id: CheckInId): CheckInDto? {
        return checkInRepository.findById(id)
            .orElse(null)
            ?.toDto()
    }



    override fun deleteById(id: CheckInId) {
        checkInRepository.deleteById(id)
    }

    override fun saveCheckIn(checkIn: CheckInDto): CheckInDto {

        val group = groupRepository.findById(checkIn.groupId)
            .orElseThrow { RuntimeException("Group not found") }

        val entity = checkIn.toEntity()
        entity.group = group

        val saved = checkInRepository.save(entity)
        return saved.toDto()
    }

    override fun findByGroupId(groupId: Int): List<CheckInDto> {
        return checkInRepository.findByGroupId(groupId)
            .map { it.toDto() }
    }

    override fun findByUserAndDateRange(
        groupId: Int,
        userId: Int,
        start: LocalDate,
        end: LocalDate
    ): List<CheckInDto> {
        return checkInRepository
            .findByGroupIdAndUserIdAndDateBetween(groupId, userId, start, end)
            .map { it.toDto() }
    }

    override fun findByGroupIdAndDate(
        groupId: Int, date: LocalDate
    ): List<CheckInDto> {
        return checkInRepository.findByGroupIdAndDate(groupId, date).map { it.toDto() }
    }
}