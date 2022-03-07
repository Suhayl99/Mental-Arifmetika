package uz.itech.mentalarifmetika.sceen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import uz.itech.mentalarifmetika.Constant.Constans
import uz.itech.mentalarifmetika.R
import uz.itech.mentalarifmetika.databinding.ActivityMainBinding
import uz.itech.mentalarifmetika.language.LocaleManager

class MainActivity : AppCompatActivity() {
    var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!
    var language: String=Constans.LANGUAGE_UZBEK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      _binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

       val navigationController= findNavController(R.id.fragmentContainerView)
      binding.bottomNavigationView.setupWithNavController(navigationController)


  }

}