package sbis.faceinfo.presentation.setting

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sbis.App
import sbis.faceinfo.R
import sbis.faceinfo.databinding.ActivitySettingBinding
import sbis.faceinfo.presentation.search.view.activity.SearchActivity

/**
 * Простой экран для ввода адреса сервера
 * */
class SettingActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent =
            Intent(context, SettingActivity::class.java).apply {
                /* put your args */
            }
    }

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SettingActivity, R.layout.activity_setting)

        val savedServerUrl = App.get().getStorageService().getServerUrl()
        with(binding) {

            etxtServerUrl.setText(savedServerUrl)

            btnOk.setOnClickListener {
                val serverUrl = etxtServerUrl.text.toString()
                App.get().getStorageService().saveServerUrl(serverUrl)

                showSearchScreen()
            }
        }
    }

    private fun showSearchScreen() {
        startActivity(SearchActivity.createIntent(this))
    }
}