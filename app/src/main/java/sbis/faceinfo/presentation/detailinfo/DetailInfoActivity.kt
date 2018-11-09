package sbis.faceinfo.presentation.detailinfo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.BitmapFactory
import android.os.Bundle
import sbis.faceinfo.R
import android.support.v7.app.AppCompatActivity
import sbis.data.model.PersonSearch
import sbis.faceinfo.databinding.ActivityDetailInfoBinding
import sbis.faceinfo.presentation.search.view.loadImgFromAsserts
import java.util.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable



class DetailInfoActivity : AppCompatActivity() {

    companion object {
        private val ARG_USER_ID = "ARG_USER_ID"
        private val ARG_USER_TEMP = "ARG_USER_TEMP"

        //todo: преобразовать в Enum
        private const val FIRST_SECTION = 0
        private const val SECOND_SECTION = 1
        fun createIntent(context: Context, userId: UUID, tempUser: PersonSearch): Intent =
            Intent(context, DetailInfoActivity::class.java).apply {
                putExtra(ARG_USER_ID, userId)
                putExtra(ARG_USER_TEMP, tempUser)
            }
    }

    private lateinit var binding: ActivityDetailInfoBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@DetailInfoActivity, R.layout.activity_detail_info)


        binding.personStatistics.setSectionIcons(
            mutableListOf(
                drawableToBitmap(resources.getDrawable(R.drawable.ic_launcher_background)),
                drawableToBitmap(resources.getDrawable(R.drawable.ic_cloud_off_black_36dp))
            )
        )

        binding.personStatistics.setSectionValue(FIRST_SECTION, 4)
        binding.personStatistics.setSectionValue(SECOND_SECTION, 9)

        val user = intent.extras.getParcelable<PersonSearch>(ARG_USER_TEMP)

        binding.txtUserName.text = "${user.secondName} ${user.name}"
        binding.imgUserProfile.setImageDrawable(loadImgFromAsserts(user.photoUrl))

    }

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null

        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }

        if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            bitmap = Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
}