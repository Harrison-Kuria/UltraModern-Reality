package ultramodern.activity.milkdiary

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_billing.*
import java.util.*

class BillingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        textView7.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year=calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_WEEK)
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    view, year, month, dayOfMonth ->
                textView8.setText("$dayOfMonth / $month / $year")
            },year,month,day)
            datePickerDialog.show()
        }
        textView9.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year=calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_WEEK)
            val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                    view, year, month, dayOfMonth ->
                textView10.setText("$dayOfMonth / $month / $year")
            },year,month,day)
            datePickerDialog.show()
        }
    }
}
