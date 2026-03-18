package com.scrumdapp.checkinservice.dto

import java.util.*

data class CheckInDto(
    val userId: Int,
    val groupId: Int,
    val date: Date?,

    val obstacle_comment: String?,
    val presence: Int?,
    var presence_comment: String?,

    val checkin_stars: Int?,
    var checkin_comment: String?,

    val checkup_stars: Int?,
    val checkup_comment: String?,

    val checkout_stars: Int?,
    val checkout_comment: String?
)