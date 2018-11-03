package sbis.faceinfo.presentation.detailinfo

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.BitmapFactory
import android.os.Bundle
import sbis.faceinfo.R
import android.support.v7.app.AppCompatActivity
import sbis.faceinfo.databinding.ActivityDetailInfoBinding
import java.util.*


class DetailInfoActivity : AppCompatActivity() {

    companion object {
        private val ARG_USER_ID = "ARG_USER_ID"

        //todo: преобразовать в Enum
        private const val FIRST_SECTION = 0
        private const val SECOND_SECTION = 1
        fun createIntent(context: Context, userId: UUID): Intent =
            Intent(context, DetailInfoActivity::class.java).apply {
                putExtra(ARG_USER_ID, userId)
            }
    }

    private lateinit var binding: ActivityDetailInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@DetailInfoActivity, R.layout.activity_detail_info)


        binding.personStatistics.setSectionIcons(
            mutableListOf(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background))
        )

        binding.personStatistics.setSectionValue(FIRST_SECTION, 4)
        binding.personStatistics.setSectionValue(SECOND_SECTION, 9)
    }
}