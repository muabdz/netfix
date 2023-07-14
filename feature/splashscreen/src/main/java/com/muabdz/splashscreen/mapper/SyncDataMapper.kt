package com.muabdz.splashscreen.mapper

import com.muabdz.shared.data.model.mapper.UserMapper
import com.muabdz.shared.utils.mapper.ViewParamMapper
import com.muabdz.splashscreen.data.network.model.response.SyncResponse
import com.muabdz.splashscreen.presentation.SyncViewParam

object SyncDataMapper: ViewParamMapper<SyncResponse, SyncViewParam> {
    override fun toViewParam(dataObject: SyncResponse?): SyncViewParam = SyncViewParam(UserMapper.toViewParam(dataObject?.userResponse))

}