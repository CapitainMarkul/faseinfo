package sbis.data.model.gson

import com.google.gson.annotations.SerializedName

data class PersonFullInfoGson(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("secondName") val secondName: String,
    @SerializedName("params") val params: List<ItemParamGson>
)