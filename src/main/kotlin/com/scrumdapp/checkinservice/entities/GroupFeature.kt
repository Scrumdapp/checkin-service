package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*

@Entity
@Table(name = "features")
class GroupFeature {

    @Id
    @Column(length = 200)
     var key: String? = null

     var description: String? = null
}