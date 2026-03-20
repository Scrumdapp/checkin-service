package com.scrumdapp.checkinservice.dto



data class GroupResponseDto(
    val id: Int,
    val name: String?,
    val background_preference: Int?,
    val icon_preference: Int?,
    val features: Set<GroupFeatureDto>
)

data class GroupCreateDto(
    val name: String,
    val background_preference: Int? = null,
    val icon_preference: Int? = null,
    val features: Set<String> = emptySet()
)

data class GroupPatchDto(
    val name: String? = null,
    val background_preference: Int? = null,
    val icon_preference: Int? = null,
    val features: Set<String>? = null
)

data class GroupFeatureDto(
    val key: String,
    val description: String?
)



