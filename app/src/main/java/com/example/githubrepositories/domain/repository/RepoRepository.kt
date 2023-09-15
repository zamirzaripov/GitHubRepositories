package com.example.githubrepositories.domain.repository

import com.example.githubrepositories.domain.entity.RepoDetails

interface RepoRepository {
    suspend fun getRepoList(): List<RepoDetails>

    suspend fun getRepoDetails(id: Int): RepoDetails
}