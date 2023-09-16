package com.example.githubrepositories.data.network

import com.example.githubrepositories.data.network.model.RepoDetailsDto
import com.example.githubrepositories.data.network.model.RepoListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/search/repositories")
    suspend fun getRepoList(@Query("q") query: String): RepoListDto

    @GET("/repositories/{repo_id}")
    suspend fun getRepoDetails(@Path("repo_id") repoId: Int): RepoDetailsDto
}