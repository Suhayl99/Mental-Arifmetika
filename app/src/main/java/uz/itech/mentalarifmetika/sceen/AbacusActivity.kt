package uz.itech.mentalarifmetika.sceen

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import uz.itech.mentalarifmetika.Constant.Constans
import uz.itech.mentalarifmetika.R
import uz.itech.mentalarifmetika.adapter.Adapter
import uz.itech.mentalarifmetika.adapter.AdapterRecyclerDialog
import uz.itech.mentalarifmetika.databinding.ActivityAbacusBinding
import uz.itech.mentalarifmetika.language.LocaleManager
import uz.itech.mentalarifmetika.model.Item
import uz.itech.mentalarifmetika.model.Model


class AbacusActivity : AppCompatActivity() {
    var _binding: ActivityAbacusBinding? = null
    val binding get() = _binding!!
    var complex=0
    var summ=0
    var natija : ArrayList<Int> = ArrayList()

  lateinit  var list:ArrayList<Int>
   lateinit var itemList: ArrayList<Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityAbacusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemList = ArrayList()
        binding.recyclerviewCount.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val model=intent.getSerializableExtra(Constans.MODEL) as Model
        val count:Int=model.count.toInt()
        val row : Int=model.row.toInt()
        val time : Long= model.time * 1000L
        complex=model.complex.toInt()
            object : CountDownTimer(time, 1000) {
                override fun onTick(duration: Long) {
                    //tTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                    //here you can have your logic to set text to edittext resource id
                    // Duration
                    val Mmin = duration / 1000 / 60
                    val Ssec = duration / 1000 % 60
                    if (Ssec < 10) {
                        binding.time.setText("$Mmin:0$Ssec")
                    } else binding.time.setText("$Mmin:$Ssec")
                }

                override fun onFinish() {
                    binding.time.setText("00:00")
                    showValuesDialog()
                }
            }.start()

        var randomPlus=0
        var randomMinus=0
        var random=0
        for (i in 1..count){
            summ=0
            list =ArrayList()
            for (j in 1..row){
                randomPlus=getRandom(complex)
                randomMinus=getRandomMinus(complex)
                random=randomPlus
                if (summ.equals(0) ||(summ<random) ){
                    random=randomPlus
                    list.add(random)
                }else{
                    random=randomMinus
                    list.add(random)
                }
                summ+=random


            }
            itemList.add(Item(i,list))

        }

    binding.recyclerviewCount.adapter=Adapter(this,itemList, natija)

        binding.stop.setOnClickListener {
            showValuesDialog()
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }
    }

    private fun getRandomMinus(complex: Int): Int {
        var current=0
        if (complex.equals(1)){
            current=(1..9).random()
            current*=-1
        }  else if (complex.equals(2)){
            current=(10..99).random()
            current*=-1
        }  else if (complex.equals(3)){
            current=(100..999).random()
            current*=-1
        }else{
            current=(1..1000).random()
            current*=-1
        }
        return current
    }

    private fun getRandom(complex: Int): Int {
        var current=0
    if (complex.equals(1)){
        current=(1..9).random()
    }  else if (complex.equals(2)){
        current=(10..99).random()
    }  else if (complex.equals(3)){
        current=(100..999).random()
    }else{
        current=(1..1000).random()
    }
    return current
    }

    private fun showValuesDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater: LayoutInflater =layoutInflater
        val dialogLayout=inflater.inflate(R.layout.dialog_total, null)
        val ImageViewClose=dialogLayout.findViewById<ImageView>(R.id.closeDialog)
        val recyclerDialog =dialogLayout.findViewById<RecyclerView>(R.id.recyclerviewDialog)
        recyclerDialog.layoutManager=LinearLayoutManager(this@AbacusActivity)
        recyclerDialog.adapter=AdapterRecyclerDialog(natija)
        with(builder){

            val buttsonStop=dialogLayout.findViewById<MaterialButton>(R.id.stopDialog)
            buttsonStop.setOnClickListener {
                startActivity(Intent(this@AbacusActivity, MainActivity::class.java))
                finish()
            }
            ImageViewClose.setOnClickListener {
                startActivity(Intent(this@AbacusActivity, MainActivity::class.java))
            finish()
            }

            setView(dialogLayout)
            show()
        }

    }

    override fun attachBaseContext(newBase: Context?){
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}