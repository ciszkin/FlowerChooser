package by.tms.flowerchooser

import androidx.lifecycle.ViewModel

class InfoCardViewModel : ViewModel() {
    var flower : Flower? = null

    fun addFlower(flowerUrl: String, flowerName: String, flowerPrice: Double) {
        flower = Flower(flowerUrl, flowerName, flowerPrice)
    }
}