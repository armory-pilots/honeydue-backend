package cloud.honeydue.model

import org.hibernate.annotations.ColumnTransformer
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name="users")
class User(
    val nickname: String,
    var emailAddress: String,
    var lastName: String,
    var firstName: String,
    @ColumnTransformer(read = "pgp_sym_decrypt(password, 'wandering-firefly-4506')", write = "pgp_sym_encrypt(?, 'wandering-firefly-4506')")
    val password: String,
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    val chores: Collection<Chore>?
    ) : AbstractJpaPersistable<Long>()