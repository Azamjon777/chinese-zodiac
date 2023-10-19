package cuttib.itmox.chinesezodiac.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cuttib.itmox.chinesezodiac.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}