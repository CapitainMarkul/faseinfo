package sbis.faceinfo.presentation.detailinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sbis.faceinfo.R
import java.util.*

class DetailInfoActivity : AppCompatActivity() {

    companion object {
        private val ARG_USER_ID = "ARG_USER_ID"

        fun createIntent(context: Context, userId: UUID): Intent =
            Intent(context, DetailInfoActivity::class.java).apply {
                putExtra(ARG_USER_ID, userId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info)
    }
}