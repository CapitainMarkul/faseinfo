package sbis.data.model.presentation

import android.os.Parcel
import sbis.helpers.arch.parcelable.KParcelable

data class PersonFullInfo(
    val id: String,
    val name: String,
    val secondName: String,
    val isSmoke: Boolean,
    val photoUrl: String,
    val params: List<ItemParam>
) : KParcelable {

    val fullName = "$secondName $name"

    companion object {
        @JvmField
        val CREATOR = KParcelable.generateCreator(::PersonFullInfo)
    }

    private constructor(p: Parcel) : this(
        id = p.readString(),
        name = p.readString(),
        secondName = p.readString(),
        isSmoke = p.readInt() == 1,
        photoUrl = p.readString(),
        params = p.createTypedArrayList(ItemParam.CREATOR)
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeString(secondName)
        writeInt(if(isSmoke) 1 else 0)
        writeString(photoUrl)
        writeTypedList(params)
    }
}