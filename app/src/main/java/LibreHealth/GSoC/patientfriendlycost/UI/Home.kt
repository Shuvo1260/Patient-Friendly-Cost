package LibreHealth.GSoC.patientfriendlycost.UI

import LibreHealth.GSoC.patientfriendlycost.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView

class Home : AppCompatActivity(), View.OnClickListener{

    lateinit var atlantic: CardView
    lateinit var baptist: CardView
    lateinit var california: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        atlantic = findViewById(R.id.atlanticareId)
        baptist = findViewById(R.id.bapistId)
        california = findViewById(R.id.californiaId)

        atlantic.setOnClickListener(this)
        baptist.setOnClickListener(this)
        california.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if (v != null) {
            var intent = Intent(this, ShowData::class.java)
            if (v.id == R.id.atlanticareId) {
                intent.putExtra("hospital", "Atlanticare Regional Medical Center")
            }else if (v.id == R.id.bapistId) {
                intent.putExtra("hospital", "Baptist Health System (San Antonio)")
            }else if (v.id == R.id.californiaId) {
                intent.putExtra("hospital", "California Pacific Medical Center")
            }
            startActivity(intent)
        }
    }


}
