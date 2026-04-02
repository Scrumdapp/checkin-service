package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate
import java.util.*

@Embeddable
data class CheckInId(var userId: Int = 0, var groupId: Int = 0, var date: LocalDate = LocalDate.MIN) : Serializable

@Entity
@Table(name = "check_in")
class CheckIn {

    @EmbeddedId
    var id: CheckInId = CheckInId()

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    var group: Group? = null

    var obstacleComment: String? = null

    var presence: Int? = null

    var presenceComment: String? = null

    var checkinStars: Int? = null

    var checkinComment: String? = null

    var checkupStars: Int? = null

    var checkupComment: String? = null

    var checkoutStars: Int? = null

    var checkoutComment: String? = null
}

