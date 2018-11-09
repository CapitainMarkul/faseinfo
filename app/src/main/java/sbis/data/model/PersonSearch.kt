package sbis.data.model

import android.os.Parcel
import sbis.helpers.arch.parcelable.KParcelable
import java.util.*

data class PersonSearch(
    val id: UUID,
    val name: String,
    val secondName: String,
    val postName: String,
    val photoUrl: String
) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = KParcelable.generateCreator(::PersonSearch)
    }

    private constructor(p: Parcel) : this(
        id = p.readSerializable() as UUID,
        name = p.readString(),
        secondName = p.readString(),
        postName = p.readString(),
        photoUrl = p.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeSerializable(id)
        writeString(name)
        writeString(secondName)
        writeString(postName)
        writeString(photoUrl)
    }
}