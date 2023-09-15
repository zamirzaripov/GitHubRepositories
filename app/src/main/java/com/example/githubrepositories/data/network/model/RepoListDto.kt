package com.example.githubrepositories.data.network.model

import com.google.gson.annotations.SerializedName

data class RepoListDto(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<RepoDetailsDto>,
    @SerializedName("total_count")
    val totalCount: Int
)