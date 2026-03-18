package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*
import java.io.Serializable
import java.util.*


data class CheckInId(val userId: Int = 0, val groupId: Int = 0, val date: Date? = null) : Serializable

@Entity
@IdClass(CheckInId::class)
@Table(name = "check_in")
class CheckIn {

    @Id
    var userId: Int = 0

    @Id
    var groupId: Int = 0

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
     var group: Group? = null

    @Id
    var date: Date? = null

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

