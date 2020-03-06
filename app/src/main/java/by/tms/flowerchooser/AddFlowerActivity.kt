package by.tms.flowerchooser

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.add_flower_layout.*

class AddFlowerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_flower_layout)

        addFlowerToList.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            addFlowerToList.id -> {
                val url = inputFlowerUrl.text
                val name = inputFlowerName.text
                val price = inputFlowerPrice.text
                if (url.isNotEmpty()
                    && name.isNotEmpty()
                    && price.isNotEmpty()) {
                    val answerIntent = Intent().apply {
                        putExtra(FLOWER_URL, url.toString())
                        putExtra(FLOWER_NAME, name.toString())
                        putExtra(FLOWER_PRICE, price.toString().toDouble())
//                        Log.e("url", url.toString())
                        url.clear()
                        name.clear()
                        price.clear()
                    }
                    setResult(Activity.RESULT_OK, answerIntent)
                    Toast.makeText(this, R.string.flower_added_success, LENGTH_SHORT).show()
                    onBackPressed()
                    //startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, R.string.fill_all_fields, LENGTH_SHORT).show()
                }
            }
        }
    }
}
