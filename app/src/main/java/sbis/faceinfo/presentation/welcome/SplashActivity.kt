package sbis.faceinfo.presentation.welcome

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import sbis.App
import sbis.faceinfo.presentation.search.view.activity.SearchActivity
import sbis.faceinfo.presentation.setting.SettingActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkAvailableServerUrl()) showSettingScreen()
        else {
            Handler().postDelayed({
                startActivity(SearchActivity.createIntent(this))
            }, 700)
        }
    }

    private fun checkAvailableServerUrl() =
        App.get().getStorageService().getServerUrl().isEmpty()

    private fun showSettingScreen() {
        startActivity(SettingActivity.createIntent(this))
    }
}
