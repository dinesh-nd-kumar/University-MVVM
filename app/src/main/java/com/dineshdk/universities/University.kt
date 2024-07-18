package com.dineshdk.universities

import com.google.gson.annotations.SerializedName

data class University (

    @SerializedName("domains")
    var domains: ArrayList<String> = arrayListOf(),
    @SerializedName("country")
    var country: String?= null,
    @SerializedName("alpha_two_code")
    var alphaTwoCode: String?= null,
    @SerializedName("web_pages")
    var webPages: ArrayList<String> = arrayListOf(),
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("state-province")
    var stateProvince : String?= null,
)