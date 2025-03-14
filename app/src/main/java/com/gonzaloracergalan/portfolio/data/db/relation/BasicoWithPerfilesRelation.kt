package com.gonzaloracergalan.portfolio.data.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.gonzaloracergalan.portfolio.data.db.entity.BasicoEntity
import com.gonzaloracergalan.portfolio.data.db.entity.PerfilEntity

data class BasicoWithPerfilesRelation(
    @Embedded
    val basico: BasicoEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val perfiles: List<PerfilEntity>
)