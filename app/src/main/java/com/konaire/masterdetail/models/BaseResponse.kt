package com.konaire.masterdetail.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Evgeny Eliseyev on 14/07/2018.
 */
open class BaseResponse {
    @SerializedName("STATUS") private val status: String = ""
}