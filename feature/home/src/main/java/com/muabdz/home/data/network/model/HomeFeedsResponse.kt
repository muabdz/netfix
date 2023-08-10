package com.muabdz.home.data.network.model

import com.google.gson.annotations.SerializedName
import com.muabdz.shared.data.model.response.MovieResponse

data class HomeFeedsResponse(
    @SerializedName("header")
    val header: MovieResponse?,
    @SerializedName("sections")
    val sections: List<SectionResponse>?
)
