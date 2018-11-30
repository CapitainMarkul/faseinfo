package sbis.data.mapper

import sbis.data.model.gson.PersonParamsGson
import sbis.data.model.gson.PersonSearchGson
import sbis.data.model.presentation.ItemParam
import sbis.data.model.presentation.Param
import sbis.data.model.presentation.PersonParams
import sbis.data.model.presentation.PersonSearch

fun PersonSearchGson.transformToPresentation() =
    PersonSearch(
        id = id,
        name = name,
        secondName = secondName,
        postName = postName,
        photoUrl = photoUrl
    )

fun List<PersonSearchGson>.transformToPresentationList() =
    map { it.transformToPresentation() }

fun PersonParamsGson.transformToPresentation() =
    PersonParams(
        oftenLeaving = oftenLeaving,
        params = listOf(
            ItemParam(Param.RESPONSIBILITY, responsibility),
            ItemParam(Param.PROCRASTINATION, procrastination),
            ItemParam(Param.SOCIABILITY, sociability),
            ItemParam(Param.PUNCTUALITY, punctuality)
        )
    )