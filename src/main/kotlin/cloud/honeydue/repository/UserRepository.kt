package cloud.honeydue.repository

import cloud.honeydue.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun getByNickname(nickname : String): User
}