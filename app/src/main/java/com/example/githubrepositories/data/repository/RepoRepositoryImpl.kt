package com.example.githubrepositories.data.repository

import android.util.Log
import com.example.githubrepositories.data.mapper.RepoMapper
import com.example.githubrepositories.data.network.ApiFactory
import com.example.githubrepositories.domain.entity.RepoDetails
import com.example.githubrepositories.domain.repository.RepoRepository

class RepoRepositoryImpl : RepoRepository {

    private val mapper = RepoMapper()
    private val apiService = ApiFactory.apiService

    override suspend fun getRepoList(): List<RepoDetails> {
        return try {
            val repoList = apiService.getRepoList("android")

            repoList.items.map {
                mapper.mapDtoToEntity(it)
            }
        } catch (e: Exception) {
            Log.e("XXXXXX", e.message.toString())
            emptyList()
        }
    }

    override suspend fun getRepoDetails(id: Int): RepoDetails {
        return try {
            val repoDetails = apiService.getRepoDetails(id)

            mapper.mapDtoToEntity(repoDetails)
        } catch (e: Exception) {
            RepoDetails("", 0, "", "", "", "", "", 0)
        }
    }
}