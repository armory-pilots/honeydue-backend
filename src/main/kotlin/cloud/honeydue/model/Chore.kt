package cloud.honeydue.model

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name="chores")
class Chore(
    val title: String,
    val description: String,
    val dueDate: Date,
    @ManyToOne
    val user: User
    ) : AbstractJpaPersistable<Long>()