package by.tms.flowerchooser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_layout.*
import kotlinx.android.synthetic.main.fragment_layout.infoCardFlowerName
import kotlinx.android.synthetic.main.fragment_layout.infoCardFlowerPrice
import kotlinx.android.synthetic.main.info_card_layout.*

class InfoCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_card_layout)

        val model = ViewModelProvider(this).get(InfoCardViewModel::class.java)

        if(model.flower == null) {
            val url = intent.getStringExtra(FLOWER_URL)
            val name = intent.getStringExtra(FLOWER_NAME)
            val price = intent.getDoubleExtra(FLOWER_PRICE, 0.0)

            model.addFlower(url ?: "", name ?: "", price)
        }

        restoreContent()
    }

    private fun restoreContent() {
        val model = ViewModelProvider(this).get(InfoCardViewModel::class.java)

        Picasso.get().load(model.flower?.url).into(infoCardFlowerImage)
        infoCardFlowerName.text = model.flower?.name
        infoCardFlowerPrice.text = model.flower?.price.toString()

    }
}
