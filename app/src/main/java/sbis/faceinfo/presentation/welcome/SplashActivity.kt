package sbis.faceinfo.presentation.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import sbis.faceinfo.presentation.search.view.activity.SearchActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, SearchActivity::class.java))
        }, 700)
    }
}
