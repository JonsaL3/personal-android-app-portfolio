package com.gonzaloracergalan.portfolio.data.db.calculated

data class ActiveResumeSectionsCalculated(
    val resumeId: Long,
    val activeResumeSections: List<ActiveResumeSection>
)