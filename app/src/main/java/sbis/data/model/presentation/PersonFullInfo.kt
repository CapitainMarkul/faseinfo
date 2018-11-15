package sbis.data.model.presentation

import android.os.Parcel
import sbis.helpers.arch.parcelable.KParcelable
import java.util.*

data class PersonFullInfo(
    val id: UUID,
    val name: String,
    val secondName: String
) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = KParcelable.generateCreator(::PersonFullInfo)
    }

    private constructor(p: Parcel) : this(
        id = p.readSerializable() as UUID,
        name = p.readString(),
        secondName = p.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeSerializable(id)
        writeString(name)
        writeString(secondName)
    }
}