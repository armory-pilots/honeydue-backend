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

	@Test
	@Order(1)
	fun createUser() {
		val u = User(nickname, "leefaus@me.com", "Faus", "Lee", "password", null)
		val hashCodeBefore = u.hashCode()
		val userSet = hashSetOf(u)
		userRepo.save(u)
		val hashCodeAfter = u.hashCode()
		assertThat(userRepo.findAll()).hasSize(1)
		assertThat(userSet).contains(u)
		assertThat(hashCodeAfter).isEqualTo(hashCodeBefore)
	}

	@Test
	@Order(2)
	fun createChore() {
		val u = userRepo.getByNickname(nickname)
		val c = Chore("Take out the trash", "Trash goes out on Wed nights", Date.valueOf("2021-04-21"), u)
		val hashCodeBefore = c.hashCode()
		val choreSet = hashSetOf(c)
		choreRepo.save(c)
		val hashCodeAfter = c.hashCode()
		assertThat(choreRepo.findAll()).hasSize(1)
		assertThat(choreSet).contains(c)
		assertThat(hashCodeAfter).isEqualTo(hashCodeBefore)
	}

	@Test
	@Order(3)
	fun testCryptoForPassword() {
		val u = userRepo.getByNickname(nickname)
		assertThat(u.password).isEqualTo("password")
	}

	@Test
	@Order(4)
	fun deleteUserWithCascadingDelete() {
		val u = userRepo.getByNickname(nickname)
		if (u != null) {
			userRepo.delete(u)
		}
		assertThat(userRepo.findAll()).hasSize(0)
		assertThat(choreRepo.findAll()).hasSize(0)
	}

}
