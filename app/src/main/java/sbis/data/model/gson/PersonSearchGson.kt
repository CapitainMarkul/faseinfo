package sbis.data.model.gson

import com.google.gson.annotations.SerializedName

data class PersonSearchGson(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("secondName") val secondName: String,
    @SerializedName("postName") val postName: String,
    @SerializedName("photoUrl") val photoUrl: String
)