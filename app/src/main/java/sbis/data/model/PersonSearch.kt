package sbis.data.model

import java.util.*

data class PersonSearch(
    val id: UUID,
    val name: String,
    val secondName: String,
    val postName: String,
    val photoUrl: String
) {
}