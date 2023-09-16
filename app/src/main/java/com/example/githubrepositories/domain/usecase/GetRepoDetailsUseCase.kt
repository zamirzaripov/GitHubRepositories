package com.example.githubrepositories.domain.usecase

import com.example.githubrepositories.domain.common.Resource
import com.example.githubrepositories.domain.entity.RepoDetails
import com.example.githubrepositories.domain.repository.RepoRepository

class GetRepoDetailsUseCase(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(id: Int): Resource<RepoDetails> = repoRepository.getRepoDetails(id)
}