package uz.itech.mentalarifmetika.sceen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.itech.mentalarifmetika.databinding.FragmentProfilBinding


class ProfilFragment : Fragment() {
    var _binding: FragmentProfilBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProfilBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ImageCall.setOnClickListener {
            val number = Uri.parse("tel:+998911309929")
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            startActivity(callIntent)
        }

        binding.imageTelegram.setOnClickListener {
          val userid :String = "mentalarifmetika2000"
 val tgintent = Intent(Intent.ACTION_VIEW,
                      Uri.parse("https://t.me/" + userid));
tgintent.setPackage("org.telegram.messenger");
startActivity(tgintent);
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfilFragment()
    }
}