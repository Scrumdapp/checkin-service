package com.scrumdapp.checkinservice.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive

data class GroupResponseDto(
    val id: Int,
    val name: String?,
    val background_preference: Int?,
    val icon_preference: Int?,
    val features: Set<String>
)

data class GroupCreateDto(

    @field:NotBlank(message = "Name is required")

    val name: String,
    @field:Positive(message = "Background id must be above 0")

    val background_preference: Int? = null,

    @field:Positive(message = "icon id must be above 0")
    val icon_preference: Int? = null,

    @field:Valid
    val features: Set<@Pattern(regexp = """"[a-z0-9]\.[a-z0-9]+(?:_[a-z0-9]+)*$""")String> = emptySet()
)

data class GroupPatchDto(
    val name: String? = null,

    @field:Positive(message = "Background id must be above 0")
    val background_preference: Int? = null,

    @field:Positive(message = "icon id must be above 0")
    val icon_preference: Int? = null,

    @field:Valid
    val features: Set<@Pattern(regexp = """"[a-z0-9]\.[a-z0-9]+(?:_[a-z0-9]+)*$""")String>? = null
)

data class GroupFeatureDto(

    val key: String,
    val description: String?
)



