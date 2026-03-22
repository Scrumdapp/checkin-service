package com.scrumdapp.checkinservice.repositories

import com.scrumdapp.checkinservice.entities.CheckIn
import com.scrumdapp.checkinservice.entities.CheckInId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface CheckinRepository : JpaRepository<CheckIn, CheckInId> {

    fun findAllByIdGroupId(groupId: Int): List<CheckIn>
    fun findAllByIdGroupIdAndIdUserId(groupId: Int, userId: Int): List<CheckIn>
    fun findAllByIdUserIdAndIdGroupIdAndIdDateBetween(userId: Int, groupId: Int, from: LocalDate, to: LocalDate): List<CheckIn>
    fun findAllById(id: CheckInId): List<CheckIn>

    fun findAllByIdGroupIdAndIdDate(groupId: Int, date: LocalDate): List<CheckIn>
}