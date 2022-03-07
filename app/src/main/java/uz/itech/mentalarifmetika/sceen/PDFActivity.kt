package uz.itech.mentalarifmetika.sceen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView
import uz.itech.mentalarifmetika.R
import uz.itech.mentalarifmetika.language.LocaleManager

class PDFActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfactivity)

        val pdfView = findViewById<PDFView>(R.id.pdfView)
        pdfView.fromAsset("mental.pdf").load()
    }

    override fun attachBaseContext(newBase: Context?){
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}