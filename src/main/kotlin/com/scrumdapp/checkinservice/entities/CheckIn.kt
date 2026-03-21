package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate
import java.util.*


data class CheckInId(val userId: Int = 0, val groupId: Int = 0, val date: LocalDate? = null) : Serializable

@Entity
@IdClass(CheckInId::class)
@Table(name = "check_in")
class CheckIn {

    @Id
    var userId: Int = 0

    @Id
    @Column(name = "group_id")  // map the FK column explicitly
    var groupId: Int = 0

    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false) // read-only, driven by groupId above
    var group: Group? = null

    @Id
    var date: LocalDate? = null

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

