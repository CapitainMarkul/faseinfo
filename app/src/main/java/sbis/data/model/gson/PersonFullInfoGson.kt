package sbis.data.model.gson

import com.google.gson.annotations.SerializedName
import sbis.data.model.presentation.ItemParam

data class PersonFullInfoGson(
    @SerializedName("userId") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("secondName") val secondName: String,
    @SerializedName("params") val params: List<ItemParam> /* FIXME: ItemParam -> ItemParamGson */
)