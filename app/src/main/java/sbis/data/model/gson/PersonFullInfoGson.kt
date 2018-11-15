package sbis.data.model.gson

import com.google.gson.annotations.SerializedName
import java.util.*

data class PersonFullInfoGson(
    @SerializedName("userId") val id: UUID,
    @SerializedName("name") val name: String,
    @SerializedName("secondName") val secondName: String
)