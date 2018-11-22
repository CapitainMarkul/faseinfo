package sbis.data.model.gson

import com.google.gson.annotations.SerializedName

data class ItemParamGson(
    @SerializedName("title") val title: String,
    @SerializedName("value") val value: Int
)