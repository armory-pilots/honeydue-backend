package cloud.honeydue

import cloud.honeydue.model.Chore
import cloud.honeydue.model.User
import cloud.honeydue.repository.ChoreRepository
import cloud.honeydue.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.sql.Date

@SpringBootTest
@ExtendWith(SpringExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class HoneyDueApplicationTests(@Autowired val userRepo: UserRepository, @Autowired val choreRepo: ChoreRepository) {
	val nickname = "continuouslee"

}
