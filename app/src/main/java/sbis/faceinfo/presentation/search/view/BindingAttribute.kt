package sbis.faceinfo.presentation.search.view

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("load_photo")
fun SimpleDraweeView.loadPhoto(imgUrl: String) {
    setImageDrawable(context.loadImgFromAsserts(imgUrl))
}


fun Context.loadImgFromAsserts(fileName: String): Drawable =
    Drawable.createFromStream(assets.open(fileName), null)