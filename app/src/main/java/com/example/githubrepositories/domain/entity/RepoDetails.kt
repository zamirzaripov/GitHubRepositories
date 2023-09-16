package com.example.githubrepositories.domain.entity

data class RepoDetails(
    val description: String? = null,
    val forks: Int,
    val fullName: String,
    val htmlUrl: String,
    val id: Int,
    val language: String? = null,
    val name: String,
    val owner: Owner,
    val updatedAt: String,
    val url: String,
    val visibility: String,
    val watchers: Int,
)
