package sbis.data.model.presentation

import android.os.Parcel
import sbis.helpers.arch.parcelable.KParcelable

data class PersonSearch(
    val id: String,
    val name: String?,
    val secondName: String?,
    val postName: String?,
    val photoUrl: String?
) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = KParcelable.generateCreator(::PersonSearch)
    }

    private constructor(p: Parcel) : this(
        id = p.readString(),
        name = p.readString(),
        secondName = p.readString(),
        postName = p.readString(),
        photoUrl = p.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeString(secondName)
        writeString(postName)
        writeString(photoUrl)
    }
}