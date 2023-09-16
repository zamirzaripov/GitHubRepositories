package com.example.githubrepositories.domain.usecase

import com.example.githubrepositories.domain.common.Resource
import com.example.githubrepositories.domain.entity.RepoDetails
import com.example.githubrepositories.domain.repository.RepoRepository

class GetRepoListUseCase(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(): Resource<List<RepoDetails>> = repoRepository.getRepoList()
}