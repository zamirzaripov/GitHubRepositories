package com.example.githubrepositories.domain.repository

import com.example.githubrepositories.domain.common.Resource
import com.example.githubrepositories.domain.entity.RepoDetails

interface RepoRepository {
    suspend fun getRepoList(): Resource<List<RepoDetails>>

    suspend fun getRepoDetails(id: Int): Resource<RepoDetails>
}