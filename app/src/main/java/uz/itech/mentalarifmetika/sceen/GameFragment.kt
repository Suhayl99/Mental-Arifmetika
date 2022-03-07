package uz.itech.mentalarifmetika.sceen

import android.R.attr.duration
import android.R.attr.visible
import android.app.AlertDialog
import android.graphics.Color.alpha
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import uz.itech.mentalarifmetika.R
import uz.itech.mentalarifmetika.databinding.FragmentGameBinding
import java.lang.Thread.sleep
import java.util.*


class GameFragment : Fragment() {
    var random:Int=0
    var total:Int=0
    var current:String=""
    var _binding: FragmentGameBinding?=null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.animationTrue.visibility=View.GONE
        binding.animationFalse.visibility=View.GONE

        binding.CardViewRepeat.setOnClickListener {
            total=0
            meRandom()
            binding.textView.visibility=View.VISIBLE
            binding.animationTrue.visibility=View.GONE
            binding.animationFalse.visibility=View.GONE
        }
        binding.tvHome.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.menuFragment2)
        }

        binding.textView.setOnClickListener {
                binding.textView.text=getString(R.string.diqqat)
            sleep(2000L)
            binding.animationTrue.visibility=View.GONE
            binding.animationFalse.visibility=View.GONE
            meRandom()
            total=0


        }






    }

    private fun meRandom() {



        val timer = object: CountDownTimer(20000, 2000) {
                override fun onTick(millisUntilFinished: Long) {
                    random=getRandoms()

                       var current=random
                       binding.textView.text=current.toString()
                        binding.textView.animate().apply {
                            duration=500
                            alpha(.1f)
                        }.withEndAction {
                            binding.textView.animate().apply {
                                duration=500
                                alpha(1f)
                            }
                        }
                       total+=current

                }

                override fun onFinish() {

                    showEditTextDialog()
                }
            }
            timer.start()

    }

    private fun getRandoms(): Int {
        var current = 0

            current = (1..50).random()

        return current
    }


    private fun showEditTextDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater:LayoutInflater=layoutInflater
        val dialogLayout=inflater.inflate(R.layout.dialog_edit_text, null)
        val editText=dialogLayout.findViewById<EditText>(R.id.Dialog_edit)

        with(builder){

            setTitle(getString(R.string.javobini_kiriting))
            setPositiveButton(getString(R.string.tekshirish)){ dialog, with ->


                binding.textView.text=total.toString()
                current=editText.text.toString()
                if (current.equals(total.toString())){
                    binding.animationTrue.visibility=View.GONE
                    binding.animationTrue.visibility=View.VISIBLE

                }else{
                    binding.animationFalse.visibility=View.VISIBLE
                    binding.animationTrue.visibility=View.GONE
                }
            }
            setView(dialogLayout)
            show()

        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = GameFragment()
    }
}