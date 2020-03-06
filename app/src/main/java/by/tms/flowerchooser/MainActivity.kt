package by.tms.flowerchooser

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.main_activity_layout.*

const val ADD_FLOWER = 0
const val FLOWER_URL = "flower url"
const val FLOWER_NAME = "flower name"
const val FLOWER_PRICE = "flower price"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var topFragment : FlowerFragment? = null
    private var bottomFragment : FlowerFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
        addTopFragment.setOnClickListener(this)
        addBottomFragment.setOnClickListener(this)
        removeTopFragment.setOnClickListener(this)
        removeBottomFragment.setOnClickListener(this)
        addFlower.setOnClickListener(this)
        topFragmentPlace.setOnClickListener(this)
        bottomFragmentPlace.setOnClickListener(this)

        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        if(model.getSize() > 0){
            setTopFragment()
            if(model.getSize() > 1) setBottomFragment()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val model = ViewModelProvider(this).get(MainViewModel::class.java)
        when(requestCode) {
            ADD_FLOWER -> {
                if (resultCode == Activity.RESULT_OK) {
                    val url = data?.getStringExtra(FLOWER_URL)
                    val name = data?.getStringExtra(FLOWER_NAME)
                    val price = data?.getDoubleExtra(FLOWER_PRICE, 0.0)

                    model.addFlower(url.toString(), name.toString(), price ?: 0.0)
                }
            }
        }
    }

    override fun onClick(view: View?) {
        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        when(view?.id) {
            addTopFragment.id -> {
                if(model.getSize() > 0) {
                    setTopFragment()
                } else {
                    Toast.makeText(this, R.string.add_flower_first, LENGTH_SHORT).show()
                    Log.e("something","size: ${model.getSize()}")
                }

            }
            removeTopFragment.id -> {
                if (topFragment != null) {
                    supportFragmentManager.beginTransaction().remove(topFragment as Fragment).commit()
                } else {
                    Toast.makeText(this, R.string.nothing_to_remove, LENGTH_SHORT).show()
                }
            }
            addBottomFragment.id -> {
                if(model.getSize() > 1) {
                    setBottomFragment()
                } else {
                    Toast.makeText(this, R.string.add_flower_first, LENGTH_SHORT).show()
                }

            }
            removeBottomFragment.id -> {
                if (bottomFragment != null) {
                    supportFragmentManager.beginTransaction().remove(bottomFragment as Fragment).commit()
                } else {
                    Toast.makeText(this, R.string.nothing_to_remove, LENGTH_SHORT).show()
                }
            }
            addFlower.id -> {
                val intent = Intent(this, AddFlowerActivity::class.java)
                startActivityForResult(intent, ADD_FLOWER)
            }
            topFragmentPlace.id -> {
                showInfoCard(0)
            }
            bottomFragmentPlace.id -> {
                showInfoCard(1)
            }
        }
    }

    private fun setTopFragment() {
        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        topFragment = FlowerFragment()
        supportFragmentManager.beginTransaction().replace(R.id.topFragmentPlace, topFragment as Fragment).commit()
        (topFragment as FlowerFragment).setFlower(model.getFlower(0))
    }

    private fun setBottomFragment() {
        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        bottomFragment = FlowerFragment()
        supportFragmentManager.beginTransaction().replace(R.id.bottomFragmentPlace, bottomFragment as Fragment).commit()
        (bottomFragment as FlowerFragment).setFlower(model.getFlower(1))
    }

    private fun showInfoCard(index: Int) {
        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        val infoIntent = Intent(this, InfoCardActivity::class.java).apply{
            putExtra(FLOWER_URL, model.getFlower(index).url)
            putExtra(FLOWER_NAME, model.getFlower(index).name)
            putExtra(FLOWER_PRICE, model.getFlower(index).price)
        }
        startActivity(infoIntent)
    }
}
