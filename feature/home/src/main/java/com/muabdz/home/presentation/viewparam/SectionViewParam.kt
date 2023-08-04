package com.muabdz.home.presentation.viewparam

import com.google.gson.annotations.SerializedName
import com.muabdz.shared.data.model.viewparam.MovieViewParam

data class SectionViewParam(
    @SerializedName("section_id")
    val sectionId: Int,
    @SerializedName("section_name")
    val sectionName: String,
    @SerializedName("contents")
    val contents: List<MovieViewParam>
)
