package com.example.githubrepositories.data.mapper

import com.example.githubrepositories.data.network.model.RepoDetailsDto
import com.example.githubrepositories.domain.entity.RepoDetails

class RepoMapper {

    private val ownerMapper = OwnerMapper()
    fun mapDtoToEntity(dto: RepoDetailsDto) = RepoDetails(
        description = dto.description,
        forks = dto.forks,
        fullName = dto.fullName,
        htmlUrl = dto.htmlUrl,
        id = dto.id,
        language = dto.language,
        name = dto.name,
        owner = ownerMapper.mapDtoToEntity(dto.owner),
        updatedAt = dto.updatedAt,
        url = dto.url,
        visibility = dto.visibility,
        watchers = dto.watchers,
    )
}