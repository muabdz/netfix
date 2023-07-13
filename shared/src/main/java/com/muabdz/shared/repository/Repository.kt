package com.muabdz.shared.repository

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.muabdz.core.base.BaseRepository
import com.muabdz.shared.data.model.response.BaseResponse
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent.inject

open class Repository : BaseRepository() {
    private val gson: Gson by inject(Gson::class.java)
    override fun <T> getErrorMessageFromApi(response: T): String {
        val responseBody = response as ResponseBody
        return try {
            val body = gson.fromJson(responseBody.string(), BaseResponse::class.java)
            body.message ?: "Api Error"
        } catch (e: JsonParseException) {
            "Api Error"
        }
    }
}