package com.example.githubrepositories.data.mapper

import com.example.githubrepositories.data.network.model.RepoDetailsDto
import com.example.githubrepositories.domain.entity.RepoDetails

class RepoMapper {
    fun mapDtoToEntity(dto: RepoDetailsDto) = RepoDetails(
        description = dto.description,
        id = dto.id,
        language = dto.language,
        name = dto.name,
        updatedAt = dto.updatedAt,
        url = dto.url,
        visibility = dto.visibility,
        watchers = dto.watchers,
    )
}