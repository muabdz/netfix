package com.muabdz.home.data.network.model

import com.google.gson.annotations.SerializedName
import com.muabdz.shared.data.model.response.MovieResponse

data class SectionResponse(
    @SerializedName("section_id")
    val sectionId: Int?,
    @SerializedName("section_name")
    val sectionName: String?,
    @SerializedName("contents")
    val contents: List<MovieResponse>
)
