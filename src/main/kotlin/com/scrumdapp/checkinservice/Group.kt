package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "groups")
class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(nullable = false)
    @Size(min = 3, max = 30)
    var name: String? = null

    @Column(nullable = true)
    var background_preference: Int? = null

    @Column(nullable = false)
    var is_active: Boolean = false

    @OneToOne(mappedBy = "group", cascade = [CascadeType.ALL])
    var feature: GroupFeature? = null
}