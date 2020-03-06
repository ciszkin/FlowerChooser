package by.tms.flowerchooser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_layout.*

class FlowerFragment : Fragment() {

    var url : String? = null
    var name : String? = null
    var price : Double? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        if (savedInstanceState != null) {
//            with(savedInstanceState) {
//                url = getString(STATE_URL)
//                name = getString(STATE_NAME)
//                price = getDouble(STATE_PRICE)
//            }
//        }

        Picasso.get().load(url).into(flowerImage)
        infoCardFlowerName.text = name
        infoCardFlowerPrice.text = price.toString()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.run{
//            putString(STATE_URL, url)
//            putString(STATE_NAME, name)
//            putDouble(STATE_PRICE, price.toString().toDouble())
//        }
//        super.onSaveInstanceState(outState)
//    }

    fun setFlower(flower: Flower) {
        this.url = flower.url
        this.name = flower.name
        this.price = flower.price
    }

//    companion object {
//        const val STATE_URL = "flowerUrl"
//        const val STATE_NAME = "flowerName"
//        const val STATE_PRICE = "flowerPrice"
//    }
}