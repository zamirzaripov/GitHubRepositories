package com.example.githubrepositories.domain.entity

data class RepoDetails(
    val description: String? = null,
    val id: Int,
    val language: String? = null,
    val name: String,
    val updatedAt: String,
    val url: String,
    val visibility: String,
    val watchers: Int,
)
