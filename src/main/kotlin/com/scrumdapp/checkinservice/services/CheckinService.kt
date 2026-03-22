package com.scrumdapp.checkinservice.services

import com.scrumdapp.checkinservice.dto.CheckinCreateDto
import com.scrumdapp.checkinservice.dto.CheckinResponseDto
import com.scrumdapp.checkinservice.dto.CheckinUpdateDto
import com.scrumdapp.checkinservice.dto.DateRange
import com.scrumdapp.checkinservice.entities.CheckIn
import com.scrumdapp.checkinservice.entities.CheckInId
import com.scrumdapp.checkinservice.mappers.CheckinMapper
import com.scrumdapp.checkinservice.repositories.CheckinRepository
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate


interface CheckInService {
    fun getByCheckinId(id: CheckInId): CheckinResponseDto
    fun getByGroup(groupId: Int, date: LocalDate?): List<CheckinResponseDto>
    fun getByGroupAndDate(groupId: Int, date: LocalDate): List<CheckinResponseDto>
    fun getByGroupAndUser(groupId: Int, userId: Int, dateRange: DateRange?): List<CheckinResponseDto>
    fun createCheckin(userId: Int, groupId: Int, checkinDto: CheckinCreateDto): CheckinResponseDto
    fun createBatchCheckin(groupId: Int, checkinDto: List<CheckinCreateDto>): List<CheckinResponseDto>
    fun updateCheckin(id: CheckInId, checkInDto: CheckinUpdateDto): CheckinResponseDto
    fun deleteById(id: CheckInId)
//    fun findByUserAndDateRange(
//        groupId: Int,
//        userId: Int,
//        start: LocalDate,
//        end: LocalDate
//    ): List<CheckInDto>
//
//    fun findByGroupIdAndDate(
//        groupId: Int,
//        date: LocalDate,
//    ): List<CheckInDto>
//
//    fun updateUserCheckIn(checkIn: CheckInDto): CheckInDto

}

@Service
class CheckInServiceImpl(
    private val checkinRepository: CheckinRepository,
) : CheckInService {

    override fun getByCheckinId(id: CheckInId): CheckinResponseDto {
        val checkin = checkinRepository.findById(id).orElse(null) ?: throw ResourceNotFoundException()
        return CheckinMapper.toDto(checkin)
    }

    override fun getByGroup(groupId: Int, date: LocalDate?): List<CheckinResponseDto> {

        val checkins: List<CheckIn> = if (date == null) {
            checkinRepository.findAllByIdGroupId(groupId)
        } else {
            checkinRepository.findAllByIdGroupIdAndIdDate(groupId, date)
        }

        return checkins.map { CheckinMapper.toDto(it) }
    }

    override fun getByGroupAndDate(
        groupId: Int,
        date: LocalDate
    ): List<CheckinResponseDto> {
        val checkins = checkinRepository.findAllByIdGroupIdAndIdDate(groupId, date)
        return checkins.map { CheckinMapper.toDto(it) }
    }

    override fun getByGroupAndUser(
        groupId: Int,
        userId: Int,
        dateRange: DateRange?
    ): List<CheckinResponseDto> {


        val checkins: List<CheckIn> = if (dateRange == null) {
            checkinRepository.findAllByIdGroupIdAndIdUserId(groupId, userId)
        } else {
            if (dateRange.to.isBefore(dateRange.from) ) throw ResourceNotFoundException() // Will change this error later
            checkinRepository.findAllByIdUserIdAndIdGroupIdAndIdDateBetween(userId, groupId, dateRange.from, dateRange.to)
        }

        return checkins.map { CheckinMapper.toDto(it) }
    }

    override fun createCheckin(
        userId: Int,
        groupId: Int,
        checkinDto: CheckinCreateDto
    ): CheckinResponseDto {
        val checkin = CheckinMapper.applyCreate(checkinDto)
        checkin.id.groupId = groupId
        checkin.id.userId = userId
        val savedCheckIn = checkinRepository.save(checkin)
        return CheckinMapper.toDto(savedCheckIn)
    }

    override fun createBatchCheckin(
        groupId: Int,
        checkinDto: List<CheckinCreateDto>
    ): List<CheckinResponseDto> {
        val checkins = checkinDto.map { CheckinMapper.applyCreate(it) }
        val savedCheckins = checkinRepository.saveAll(checkins)
        return savedCheckins.map { CheckinMapper.toDto(it) }
    }

    override fun updateCheckin(
        id: CheckInId,
        checkInDto: CheckinUpdateDto
    ): CheckinResponseDto {
        val checkin = checkinRepository.findById(id).orElse(null) ?: throw ResourceNotFoundException()
        CheckinMapper.applyPatch(checkin, checkInDto)
        val savedCheckIn = checkinRepository.save(checkin)
        return CheckinMapper.toDto(savedCheckIn)
    }


    override fun deleteById(id: CheckInId) {
        checkinRepository.deleteById(id)
    }



//    override fun updateUserCheckIn(checkIn: CheckInDto): CheckInDto {
//        val updated = checkinRepository.save(checkIn.toEntity())
//        return updated.toDto()
//    }
//
//    override fun findByGroupId(groupId: Int): List<CheckInDto> {
//        return checkinRepository.findByGroupId(groupId)
//            .map { it.toDto() }
//    }
//
//    override fun findByUserAndDateRange(
//        groupId: Int,
//        userId: Int,
//        start: LocalDate,
//        end: LocalDate
//    ): List<CheckInDto> {
//        return checkinRepository
//            .findByGroupIdAndUserIdAndDateBetween(groupId, userId, start, end)
//            .map { it.toDto() }
//    }
//
//    override fun findByGroupIdAndDate(
//        groupId: Int, date: LocalDate
//    ): List<CheckInDto> {
//        return checkinRepository.findByGroupIdAndDate(groupId, date).map { it.toDto() }
//    }
}