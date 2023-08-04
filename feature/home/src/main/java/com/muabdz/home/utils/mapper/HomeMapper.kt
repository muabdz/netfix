package com.muabdz.home.utils.mapper

import com.muabdz.home.data.network.model.HomeFeedsResponse
import com.muabdz.home.data.network.model.SectionResponse
import com.muabdz.home.presentation.viewparam.HomeFeedsViewParam
import com.muabdz.home.presentation.viewparam.SectionViewParam
import com.muabdz.shared.data.model.mapper.MovieMapper
import com.muabdz.shared.utils.mapper.ListMapper
import com.muabdz.shared.utils.mapper.ViewParamMapper

object HomeMapper: ViewParamMapper<HomeFeedsResponse, HomeFeedsViewParam> {
    override fun toViewParam(dataObject: HomeFeedsResponse?): HomeFeedsViewParam = HomeFeedsViewParam(
        MovieMapper.toViewParam(dataObject?.header),
        ListMapper(SectionMapper).toViewParams(dataObject?.sections)
    )
}

object SectionMapper: ViewParamMapper<SectionResponse, SectionViewParam> {
    override fun toViewParam(dataObject: SectionResponse?): SectionViewParam = SectionViewParam(
        sectionId = dataObject?.sectionId ?: -1,
        sectionName = dataObject?.sectionName.orEmpty(),
        contents = ListMapper(MovieMapper).toViewParams(dataObject?.contents)
    )

}