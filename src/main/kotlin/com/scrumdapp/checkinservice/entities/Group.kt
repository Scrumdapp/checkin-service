package com.scrumdapp.checkinservice.entities

import jakarta.persistence.*

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
     var id: Int? = null

     var name: String? = null

     var backgroundId: Int? = null

     var iconId: Int? = null

    @ManyToMany
    @JoinTable(
        name = "group_feature",
        joinColumns = [JoinColumn(name = "group_id")],
        inverseJoinColumns = [JoinColumn(name = "group_feature_key")]
    )
     var features: MutableSet<GroupFeature> = mutableSetOf()


}