package sbis.faceinfo.presentation.detailinfo.view

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import sbis.data.model.presentation.ItemParam
import sbis.data.model.presentation.Param
import sbis.faceinfo.R
import sbis.helpers.view.circle.CustomCircle

@BindingAdapter("user_photo")
fun CircleImageView.userPhoto(imgUrl: String?) {
    Picasso.get()
        .load(imgUrl)
        .placeholder(R.drawable.user_stub)
        .error(R.drawable.user_stub)
//        .memoryPolicy(MemoryPolicy.NO_CACHE)
//        .networkPolicy(NetworkPolicy.NO_CACHE)
        .into(this)
}

@BindingAdapter("img_selector")
fun ImageView.setParamImg(itemParam: Param?) {
    itemParam?.let {
        val drawableId = when (itemParam) {
            Param.PUNCTUALITY -> R.drawable.ic_punctual
            Param.PROCRASTINATION -> R.drawable.ic_procrastination
            Param.SOCIABILITY -> R.drawable.ic_sociable
            Param.RESPONSIBILITY -> R.drawable.ic_responsible
            Param.LEAVING_STATE -> R.drawable.ic_run_man_black
        }

        setImageDrawable(context.getDrawable(drawableId))
    }
}

@BindingAdapter("cc_setup")
fun CustomCircle.circleSetup(itemParam: ItemParam?) {
    itemParam?.let {
        setTextSize(16F)
        setTextColor(R.color.colorPrimary)
        setPadding(4F)
        setParamValue(itemParam.value)
        setInverseColors(itemParam.param.isNegative)
        setCenterParam(itemParam.param == Param.SOCIABILITY)
        setEnabledView(itemParam.value != -1)

        invalidate()
    }
}

@BindingAdapter("inactive_view")
fun TextView.inactiveView(itemParam: ItemParam?) {
    itemParam?.let {
        if (it.value == -1) {
            setTextColor(context.getColor(R.color.colorTextSecondary))
        } else {
            setTextColor(context.getColor(R.color.colorTextPrimary))
        }
    }
}

@BindingAdapter("inactive_view_img")
fun ImageView.inactiveViewImg(itemParam: ItemParam?) {
    itemParam?.let {
        val colorFilter = if (it.value == -1) R.color.colorTextSecondary else R.color.colorTintDefault
        setColorFilter(
            ContextCompat.getColor(context, colorFilter), android.graphics.PorterDuff.Mode.SRC_IN
        )
    }
}