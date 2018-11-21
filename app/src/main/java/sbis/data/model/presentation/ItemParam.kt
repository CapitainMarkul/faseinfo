package sbis.data.model.presentation

import android.os.Parcel
import sbis.helpers.arch.parcelable.KParcelable

data class ItemParam(
    val title: String,
    val value: Int
) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = KParcelable.generateCreator(::ItemParam)
    }

    private constructor(p: Parcel) : this(
        title = p.readString(),
        value = p.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeInt(value)
    }
}