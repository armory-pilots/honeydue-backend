package cloud.honeydue.repository

import cloud.honeydue.model.Chore
import cloud.honeydue.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface ChoreRepository : JpaRepository<Chore, Long> {
    fun findByUserId(userId: Long?) : MutableSet<Chore>
}