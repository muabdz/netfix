package com.muabdz.home.presentation.viewparam.homeitem

import com.muabdz.home.presentation.viewparam.SectionViewParam
import com.muabdz.shared.data.model.viewparam.MovieViewParam

sealed class HomeUiItem {
    class HeaderSectionItem(val movieViewParam: MovieViewParam): HomeUiItem()
    class MovieSectionItem(val sectionViewParam: SectionViewParam): HomeUiItem()
}
