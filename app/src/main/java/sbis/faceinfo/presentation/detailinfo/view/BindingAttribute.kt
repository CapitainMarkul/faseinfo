package sbis.faceinfo.presentation.detailinfo.view

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import sbis.data.model.presentation.ItemParam
import sbis.faceinfo.R
import sbis.helpers.view.circle.CustomCircle
import kotlin.random.Random

@BindingAdapter("user_photo")
fun CircleImageView.userPhoto(imgUrl: String?) {
    Picasso.get()
        .load(imgUrl)
        .error(R.drawable.ic_no_smoke_36dp)
//                    .centerCrop()
        .into(this)
}

@BindingAdapter("smoke_img")
fun ImageView.smokeImg(isSmoke: Boolean?) {
    isSmoke?.let {
        setImageDrawable(
            context.getDrawable(
                if (it) R.drawable.ic_smoke_36dp else R.drawable.ic_no_smoke_36dp
            )
        )
    }
}

@BindingAdapter("cc_setup")
fun CustomCircle.circleSetup(itemParam: ItemParam?) {
    itemParam?.let {
        setTextSize(16F)
        setTextColor(R.color.colorPrimary)
        setPadding(4F)
        setParamValue(Random.nextInt(1, 11))
        setInverseColors(Random.nextBoolean())

        invalidate()
    }
}