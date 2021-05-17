package cloud.honeydue.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name="chores")
class Chore(
    val title: String,
    val description: String?,
    val dueDate: Date,
    val completed: Boolean = false,
    @ManyToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "user_id")
    @JsonIgnore
    var user: User?
    ) : AbstractJpaPersistable<Long>()