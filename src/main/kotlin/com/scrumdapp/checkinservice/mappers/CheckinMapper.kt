package com.scrumdapp.checkinservice.mappers

import com.scrumdapp.checkinservice.dto.CheckinCreateDto
import com.scrumdapp.checkinservice.dto.CheckinResponseDto
import com.scrumdapp.checkinservice.dto.CheckinUpdateDto
import com.scrumdapp.checkinservice.entities.CheckIn


object CheckinMapper {
    fun toDto(entity: CheckIn): CheckinResponseDto =
        CheckinResponseDto(
            userId = entity.id.userId,
            groupId = entity.id.groupId,
            date = entity.id.date,
            presence = entity.presence,
            presence_comment = entity.presenceComment,

            obstacle_comment = entity.obstacleComment,

            checkin_stars = entity.checkinStars,
            checkin_comment = entity.checkinComment,

            checkup_stars = entity.checkupStars,
            checkup_comment = entity.checkupComment,

            checkout_stars = entity.checkoutStars,
            checkout_comment = entity.checkoutComment,
        )

    fun applyCreate(dto: CheckinCreateDto): CheckIn {
        return CheckIn().apply {
            id.date = dto.date!!
            id.userId = dto.userId!! // For single checkins this is overridden by service
            presence = dto.presence
            presenceComment = dto.presence_comment

            obstacleComment = dto.obstacle_comment

            checkinStars = dto.checkin_stars
            checkinComment = dto.checkin_comment

            checkupStars = dto.checkup_stars
            checkupComment = dto.checkup_comment

            checkoutStars = dto.checkup_stars
            checkoutComment = dto.checkout_comment
        }
    }

    fun applyPatch(entity: CheckIn, dto: CheckinUpdateDto): CheckIn {
        if (dto.presence != null) entity.presence = dto.presence
        if (dto.presence_comment != null) entity.presenceComment = dto.presence_comment

        if (dto.obstacle_comment != null) entity.obstacleComment = dto.obstacle_comment

        if (dto.checkin_stars != null) entity.checkinStars = dto.checkin_stars
        if (dto.checkin_comment != null) entity.checkinComment = dto.checkin_comment

        if (dto.checkup_stars != null) entity.checkupStars = dto.checkup_stars
        if (dto.checkup_comment != null) entity.checkupComment = dto.checkup_comment

        if (dto.checkout_stars != null) entity.checkoutStars = dto.checkout_stars
        if (dto.checkout_comment != null) entity.checkoutComment = dto.checkout_comment

        return entity
    }
}