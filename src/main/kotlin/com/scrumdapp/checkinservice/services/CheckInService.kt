package com.scrumdapp.checkinservice.services

import com.scrumdapp.checkinservice.entities.CheckIn
import com.scrumdapp.checkinservice.entities.CheckInId
import com.scrumdapp.checkinservice.repositories.CheckInRepository;
import org.springframework.stereotype.Service

interface CheckInService {
fun findById(id: CheckInId): CheckIn?
fun deleteById(id: CheckInId)
fun createCheckIn(checkIn: CheckIn): CheckIn
fun updateCheckIn(checkIn: CheckIn): CheckIn
}

@Service
class CheckInServiceImpl(
    private val checkInRepository: CheckInRepository,
) : CheckInService {
    override fun findById(id: CheckInId): CheckIn? {
        return checkInRepository.findById(id).orElse(null)
    }

    override fun deleteById(id: CheckInId) {
        checkInRepository.deleteById(id)
    }

    override fun createCheckIn(checkIn: CheckIn): CheckIn {
        return checkInRepository.save(checkIn)
    }

    override fun updateCheckIn(checkIn: CheckIn): CheckIn {
        return checkInRepository.save(checkIn)
    }
}