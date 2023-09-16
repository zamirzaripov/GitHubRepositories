package com.example.githubrepositories.data.repository

import com.example.githubrepositories.data.mapper.RepoMapper
import com.example.githubrepositories.data.network.ApiFactory
import com.example.githubrepositories.domain.common.Resource
import com.example.githubrepositories.domain.entity.RepoDetails
import com.example.githubrepositories.domain.repository.RepoRepository
import java.io.IOException

class RepoRepositoryImpl : RepoRepository {

    private val mapper = RepoMapper()
    private val apiService = ApiFactory.apiService

    override suspend fun getRepoList(): Resource<List<RepoDetails>> {
        return try {
            val repoList = apiService.getRepoList("android")

            Resource.Success(repoList.items.map { mapper.mapDtoToEntity(it) })
        } catch (e: IOException) {
            Resource.Error("Пожалуйста, проверьте соеденение с интернетом")
        } catch (e: Exception) {
            Resource.Error("Что-то пошло не так")
        }
    }

    override suspend fun getRepoDetails(id: Int): Resource<RepoDetails> {
        return try {
            val repoDetails = apiService.getRepoDetails(id)

            Resource.Success(mapper.mapDtoToEntity(repoDetails))
        } catch (e: IOException) {
            Resource.Error("Пожалуйста, проверьте соеденение с интернетом")
        } catch (e: Exception) {
            Resource.Error("Что-то пошло не так")
        }
    }
}