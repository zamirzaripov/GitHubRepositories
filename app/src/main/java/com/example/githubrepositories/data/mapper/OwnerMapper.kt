package com.example.githubrepositories.data.mapper

import com.example.githubrepositories.data.network.model.OwnerDto
import com.example.githubrepositories.domain.entity.Owner

class OwnerMapper {
    fun mapDtoToEntity(dto: OwnerDto) = Owner(
        avatarUrl = dto.avatarUrl
    )
}