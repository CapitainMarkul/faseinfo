package sbis.data.mapper

import sbis.data.model.gson.PersonFullInfoGson
import sbis.data.model.gson.PersonSearchGson
import sbis.data.model.presentation.PersonFullInfo
import sbis.data.model.presentation.PersonSearch

fun PersonSearchGson.transformToPresentation() =
    PersonSearch(
        id = id,
        name = name,
        secondName = secondName,
        postName = postName,
        photoUrl = photoUrl
    )

fun PersonFullInfoGson.transformToPresentation() =
    PersonFullInfo(
        id = id,
        name = name,
        secondName = secondName
    )