package ultramodern.activity.milkdiary

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.JsonWriter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_my_milk.*
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.lang.System.out
import java.util.*

class MyMilk : AppCompatActivity() {
    private val REQUEST_CONTACT = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_milk)

        button2.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year=calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_WEEK)
            val datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth ->
                textView11.setText("$day / $month / $year")
            },year,month,day)
            datePickerDialog.show()
        }

        button1.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE)
            startActivityForResult(intent,REQUEST_CONTACT)
        }
        litresinput.setError("Please Fill in this space")


        button4.setOnClickListener {
            jsonwrite()
            SQLiteHelperClass().addqty()
            Toast.makeText(applicationContext,"Record Saved Successfully",Toast.LENGTH_LONG).show()

        }
        sendsmsbutton.setOnClickListener {
            //val destinationcontact = Uri.parse("smsto:0712311209")
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,"CONFIRMED. Your today's milk sale was 20 litres")
            intent.type = "text/plain"
            startActivity(intent)

        }
    }
    fun jsonwrite(){
        val jsonwriter = JsonWriter(OutputStreamWriter(out,"UTF-8"))
        jsonwriter.setIndent("${litresinput.text}")
        jsonwriter.close()
    }

}
