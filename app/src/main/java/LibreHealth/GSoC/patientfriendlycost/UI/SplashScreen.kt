package LibreHealth.GSoC.patientfriendlycost.UI

import LibreHealth.GSoC.patientfriendlycost.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent: Intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }, 5000)

    }
}
