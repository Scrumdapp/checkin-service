package com.scrumdapp.checkinservice.repositories

import com.scrumdapp.checkinservice.entities.CheckIn
import com.scrumdapp.checkinservice.entities.CheckInId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface CheckInRepository : JpaRepository<CheckIn, CheckInId> {
    fun findByGroupId(groupId: String): List<CheckIn>
}
