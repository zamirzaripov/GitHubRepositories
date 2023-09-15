package com.example.githubrepositories.data.network.model

import com.google.gson.annotations.SerializedName

data class LicenseDto(
    val key: String,
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    val url: String
)