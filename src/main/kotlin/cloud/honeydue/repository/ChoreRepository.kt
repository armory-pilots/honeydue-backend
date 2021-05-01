package cloud.honeydue.repository

import cloud.honeydue.model.Chore
import org.springframework.data.jpa.repository.JpaRepository

interface ChoreRepository : JpaRepository<Chore, Long> {
    fun findAllByUser(id: Long)
}