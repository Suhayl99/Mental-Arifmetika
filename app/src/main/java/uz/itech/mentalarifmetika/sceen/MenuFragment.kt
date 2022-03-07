package uz.itech.mentalarifmetika.sceen

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import io.paperdb.Paper
import uz.itech.mentalarifmetika.Constant.Constans
import uz.itech.mentalarifmetika.R
import uz.itech.mentalarifmetika.databinding.FragmentMenuBinding
import uz.itech.mentalarifmetika.language.LocaleManager.setNewLocale
import uz.itech.mentalarifmetika.model.Model


class MenuFragment : Fragment() {
    var _binding: FragmentMenuBinding?=null
    val binding get() = _binding!!
    var murakkab:Byte=1
    var timecurrent:Int=300
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timecurrentarray=resources.getStringArray(R.array.autoCompliteTextView)
        val adapter=ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line, timecurrentarray)
        binding.autoCompleteProviceType.setAdapter(adapter)
        binding.autoCompleteProviceType.setOnItemClickListener { adapterView, view, i, l ->
            if (i.equals(0)){
                timecurrent=30
            }else if (i.equals(1)){
                timecurrent=60
            }else if (i.equals(2)){
                timecurrent=90
            }else if (i.equals(3)){
                timecurrent=120
            }else if (i.equals(4)){
                timecurrent=180
            }else if (i.equals(5)){
                timecurrent=240
            }else if (i.equals(6)){
                timecurrent=300
            }else{
                timecurrent=60
            }
        }
     binding.animationStart.setOnClickListener {
         if (binding.editmisol.text.isNotEmpty() && binding.editqator.text.isNotEmpty()){
             var intent= Intent(requireContext(), AbacusActivity::class.java)
             val editCount=binding.editmisol.text.toString()
             val editRow=binding.editqator.text.toString()
             intent.putExtra(Constans.MODEL, Model(editCount,editRow,timecurrent, murakkab))
             startActivity(intent)
         }else{
             Toast.makeText(requireContext(), getString(R.string.qiymat_kiriting), Toast.LENGTH_SHORT).show()
         }
     }

     binding.animationDown.setOnClickListener {
         startActivity(Intent(requireContext(), PDFActivity::class.java))
     }
        binding.btn1.setOnClickListener {
            binding.btn1.setBackgroundColor(Color.BLUE)
        binding.btn2.setBackgroundColor(Color.WHITE)
        murakkab=2
        binding.btn3.setBackgroundColor(Color.WHITE)}

        binding.btn2.setOnClickListener {
            binding.btn2.setBackgroundColor(Color.BLUE)
            murakkab=3
            binding.btn1.setBackgroundColor(Color.WHITE)
            binding.btn3.setBackgroundColor(Color.WHITE)}

        binding.btn3.setOnClickListener {
            binding.btn3.setBackgroundColor(Color.BLUE)
            murakkab=0
            binding.btn1.setBackgroundColor(Color.WHITE)
            binding.btn2.setBackgroundColor(Color.WHITE)}


        binding.imgMenu.setOnClickListener {
           showLanguageDialog()
        }
    }

    private fun showLanguageDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater:LayoutInflater=layoutInflater
        val dialogLayout=inflater.inflate(R.layout.item_dialog_language, null)
        val btnUzbek=dialogLayout.findViewById<MaterialButton>(R.id.btnUzbek)
        val btnKrill=dialogLayout.findViewById<MaterialButton>(R.id.btnKirill)
        val btnEnglish=dialogLayout.findViewById<MaterialButton>(R.id.btnEnglish)

        with(builder){

                btnUzbek.setOnClickListener {
                    Paper.book().write(Constans.SharedPref, Constans.LANGUAGE_UZBEK)
                    setNewLocale(requireContext(), Constans.LANGUAGE_UZBEK)
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                }
                btnEnglish.setOnClickListener {
                    Paper.book().write(Constans.SharedPref, Constans.LANGUAGE_ENGLISH)
                    setNewLocale(requireContext(), Constans.LANGUAGE_ENGLISH)
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                }
                btnKrill.setOnClickListener {

                    Paper.book().write(Constans.SharedPref, Constans.LANGUAGE_KIRILL)
                    setNewLocale(requireContext(), Constans.LANGUAGE_KIRILL)
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                }
            setView(dialogLayout)
            show()

        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = MenuFragment()

    }
}