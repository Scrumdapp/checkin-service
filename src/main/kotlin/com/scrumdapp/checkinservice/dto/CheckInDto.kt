package com.scrumdapp.checkinservice.dto

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonView
import com.scrumdapp.checkinservice.utils.validators.DateRange
import jakarta.validation.constraints.Positive
import java.time.LocalDate


// Old Dto

//@JsonInclude(JsonInclude.Include.NON_NULL)
//data class CheckInDto(
//    val userId: Int,
//    val groupId: Int,
//    val date: LocalDate?,
//
//    val obstacle_comment: String?,
//    val presence: Int?,
//    val presence_comment: String?,
//
//    val checkin_stars: Int?,
//    val checkin_comment: String?,
//
//    val checkup_stars: Int?,
//    val checkup_comment: String?,
//
//    val checkout_stars: Int?,
//    val checkout_comment: String?
//)

data class DateRange(
    val from: LocalDate,
    val to: LocalDate
)

data class CheckinResponseDto(

    val userId: Int,
    val groupId: Int,
    val date: LocalDate?,

    val obstacle_comment: String?,

    val presence: Int? = null,
    val presence_comment: String? = null,

    val checkin_stars: Int? = null,
    val checkin_comment: String? = null,

    val checkup_stars: Int? = null,
    val checkup_comment: String? = null,

    val checkout_stars: Int? = null,
    val checkout_comment: String? = null
)

data class CheckinCreateDto(

    @field:DateRange(maxPastDays = 7, maxFutureDays = 7)
    val date: LocalDate?,
    val userId: Int? = null,

    val obstacle_comment: String? = null,

    @field:Positive(message = "presence must have a value above 0")
    val presence: Int? = null,
    val presence_comment: String? = null,

    @field:Positive(message = "stars must have values above 0")
    val checkin_stars: Int? = null,
    val checkin_comment: String? = null,

    @field:Positive(message = "stars must have values above 0")
    val checkup_stars: Int? = null,
    val checkup_comment: String? = null,

    @field:Positive(message = "stars must have values above 0")
    val checkout_stars: Int? = null,
    val checkout_comment: String? = null
)

data class CheckinUpdateDto(
    @field:Positive(message = "presence must have a value above 0")
    val presence: Int? = null,
    val presence_comment: String? = null,

    val obstacle_comment: String? = null,

    @field:Positive(message = "stars must have values above 0")
    val checkin_stars: Int? = null,
    val checkin_comment: String? = null,

    @field:Positive(message = "stars must have values above 0")
    val checkup_stars: Int? = null,
    val checkup_comment: String? = null,

    @field:Positive(message = "stars must have values above 0")
    val checkout_stars: Int? = null,
    val checkout_comment: String? = null
)