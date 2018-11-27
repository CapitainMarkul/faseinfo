package sbis.data.mapper

import sbis.data.model.gson.ItemParamGson
import sbis.data.model.gson.PersonFullInfoGson
import sbis.data.model.gson.PersonSearchGson
import sbis.data.model.presentation.ItemParam
import sbis.data.model.presentation.PersonFullInfo
import sbis.data.model.presentation.PersonSearch

//TODO: PersonSearchGson to PersonSearch
//TODO: List<PersonSearchGson> to List<PersonSearch>

//TODO: PersonFullInfoGson to PersonFullInfo
//TODO: List<PersonFullInfoGson> to List<PersonFullInfo>


// ============ ПРИМЕР ============
fun ItemParamGson.transformToPresentation() =
    ItemParam(
        title = title,
        value = value
    )

fun List<ItemParamGson>.transformToPresentationItemParamList() =
    map { it.transformToPresentation() }