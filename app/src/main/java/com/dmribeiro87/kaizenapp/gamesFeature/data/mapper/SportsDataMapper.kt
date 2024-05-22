package com.dmribeiro87.kaizenapp.gamesFeature.data.mapper

import com.dmribeiro87.kaizenapp.core.data.local.entity.EventEntity
import com.dmribeiro87.kaizenapp.core.data.remote.model.EventResponse
import com.dmribeiro87.kaizenapp.core.data.remote.model.SportResponseItem
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports

fun EventResponse.toEvent(): Event {
    val competitors = sliceCompetitors(this.description)
    val currentTime = System.currentTimeMillis() / 1000
    val remainingTime = this.startTime - currentTime
    return Event(
        id = this.id,
        sportId = this.sportId,
        competitorOne = competitors.first,
        competitorTwo = competitors.second,
        shortDescription = this.shortDescription,
        startTime = this.startTime,
        remainingTime = remainingTime,
        isFavorite = false
    )
}

fun SportResponseItem.toSport(): Sports {
    return Sports(
        id = this.id,
        name = this.name,
        events = this.events.map { it.toEvent() }
    )
}


fun sliceCompetitors(description: String): Pair<String, String> {
    val competitors = description.split(" - ")
    return Pair(competitors[0], competitors[1])
}

fun Event.toEntity(): EventEntity {
    return EventEntity(
        id = this.id,
        sportId = this.sportId,
        competitorOne = this.competitorOne,
        competitorTwo = this.competitorTwo,
        shortDescription = this.shortDescription,
        startTime = this.startTime,
        isFavorite = this.isFavorite
    )
}

fun EventEntity.toDomain(): Event {
    return Event(
        id = this.id,
        sportId = this.sportId,
        competitorOne = this.competitorOne,
        competitorTwo = this.competitorTwo,
        shortDescription = this.shortDescription,
        startTime = this.startTime,
        remainingTime = this.startTime - System.currentTimeMillis() / 1000,
        isFavorite = this.isFavorite
    )
}
