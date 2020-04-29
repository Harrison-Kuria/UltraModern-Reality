package ultramodern.activity.milkdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_available__options.*

class Available_Options : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available__options)

        imageView1.setOnClickListener {
            val intent = Intent(this,MyMilk::class.java)
            startActivity(intent)
        }
        textView4.setOnClickListener {
            val intent = Intent(this,MyMilk::class.java)
            startActivity(intent)
        }
        imageView3.setOnClickListener {
            val intent = Intent(this,BillingActivity::class.java)
            startActivity(intent)
        }
        textView3.setOnClickListener {
            val intent = Intent(this,BillingActivity::class.java)
            startActivity(intent)
        }
        imageView4.setOnClickListener {
            val intent = Intent(this,Summary::class.java)
            startActivity(intent)
        }
        textView5.setOnClickListener {
            val intent = Intent(this,Summary::class.java)
            startActivity(intent)
        }
    }
}
