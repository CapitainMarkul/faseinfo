package sbis.faceinfo.presentation.detailinfo.view

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import sbis.faceinfo.R


@BindingAdapter("user_photo")
fun CircleImageView.userPhoto(imgUrl: String?) {
    Picasso.get()
        .load(imgUrl)
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