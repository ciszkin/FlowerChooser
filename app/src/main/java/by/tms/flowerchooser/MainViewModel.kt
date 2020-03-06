package by.tms.flowerchooser

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val listOfFlowers = ArrayList<Flower>()

    fun addFlower(flowerUrl: String, flowerName: String, flowerPrice: Double) {
        listOfFlowers.add(Flower(flowerUrl, flowerName, flowerPrice))
//        Log.e("something","Flower added.")
//        listOfFlowers.forEach {
//            Log.e("flowers","Name: ${it.name}")
//        }

    }

//    fun getTopFragment(): FlowerFragment {
//        return getFragment(0)
//    }
//    fun getBottomFragment() : FlowerFragment {
//        return getFragment(1)
//    }
    fun getSize(): Int {
//        Log.e("something", listOfFlowers.size.toString())
        return listOfFlowers.size
    }

    fun getFlower(index: Int) : Flower {
        return listOfFlowers[index]
    }
}