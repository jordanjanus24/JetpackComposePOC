package com.app.compose.domain.util

interface EntityMapper <T, EntityModel>{

    fun mapToEntityModel(model: T): EntityModel

    fun mapFromEntityModel(entity: EntityModel): T
}