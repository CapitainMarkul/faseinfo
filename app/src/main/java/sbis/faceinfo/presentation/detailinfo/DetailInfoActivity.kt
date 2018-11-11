package sbis.faceinfo.presentation.detailinfo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
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
import android.support.design.widget.AppBarLayout
import android.view.View
import android.view.animation.AlphaAnimation


class DetailInfoActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    companion object {
        private const val PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.8f
        private const val PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.1f
        private const val ALPHA_ANIMATIONS_DURATION = 200


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

    private var isTitleVisible = false
    private var isTitleContainerVisible = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@DetailInfoActivity, R.layout.activity_detail_info)


        binding.appBar.addOnOffsetChangedListener(this)
        startAlphaAnimation(binding.txtUserName, 0, View.INVISIBLE)


        binding.personStatistics.setSectionIcons(
            mutableListOf(
                drawableToBitmap(resources.getDrawable(R.drawable.ic_launcher_background)),
                drawableToBitmap(resources.getDrawable(R.drawable.ic_cloud_off_black_36dp))
            )
        )

        binding.personStatistics.setSectionValue(FIRST_SECTION, 4)
        binding.personStatistics.setSectionValue(SECOND_SECTION, 9)

        val user = intent.extras.getParcelable<PersonSearch>(ARG_USER_TEMP)

        //todo: перевести в dataBinding
        binding.txtUserName.text = "${user.secondName} ${user.name}"
        binding.txtUserNameTitle.text = "${user.secondName} ${user.name}"
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

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(verticalOffset).toFloat() / maxScroll.toFloat()

        handleAlphaOnTitle(percentage)
        handleToolbarTitleVisibility(percentage)
    }

    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR && !isTitleVisible) {
            startAlphaAnimation(binding.txtUserName, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
            isTitleVisible = true
        } else if (percentage < PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR && isTitleVisible) {
            startAlphaAnimation(binding.txtUserName, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
            isTitleVisible = false
        }
    }

    private fun handleAlphaOnTitle(percentage: Float) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS && isTitleContainerVisible) {
            startAlphaAnimation(binding.titleCustom, ALPHA_ANIMATIONS_DURATION.toLong(), View.INVISIBLE)
            isTitleContainerVisible = false
        } else if (percentage < PERCENTAGE_TO_HIDE_TITLE_DETAILS && !isTitleContainerVisible) {
            startAlphaAnimation(binding.titleCustom, ALPHA_ANIMATIONS_DURATION.toLong(), View.VISIBLE)
            isTitleContainerVisible = true
        }
    }

    private fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation =
            if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f)
            else AlphaAnimation(1f, 0f)

        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }
}