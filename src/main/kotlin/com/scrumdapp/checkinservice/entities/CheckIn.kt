package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*
import java.io.Serializable
import java.util.*


class CheckInId(private val UserId: Int, private val GroupId: Int, private val Date: Date?) : Serializable

@Entity
@IdClass(CheckInId::class)
class CheckIn {

    @Id
    var userId: Int = 0

    @Id
    var groupId: Int = 0

    @ManyToOne
    @PrimaryKeyJoinColumn(name="groupId", referencedColumnName = "id")
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

