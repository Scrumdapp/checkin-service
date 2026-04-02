package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate

@Entity
@Table(name = "group_features")
class GroupFeature(

    @Id
    var key: Int? = null,

    @OneToOne
    @MapsId
    @JoinColumn(name = "key")
    var group: Group? = null,

    @Column(nullable = false)
    var description: String? = null
)