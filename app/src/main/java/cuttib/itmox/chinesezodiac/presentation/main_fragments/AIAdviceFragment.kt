package cuttib.itmox.chinesezodiac.presentation.main_fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.FragmentAIAdviceBinding
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModel
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModelFactory

class AIAdviceFragment : Fragment() {
    private lateinit var binding: FragmentAIAdviceBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: ZodiacViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAIAdviceBinding.inflate(inflater, container, false)
        val factory = ZodiacViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, factory)[ZodiacViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        clicks()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun clicks() {
        val shrinkAnimation = ScaleAnimation(
            1.0f, 0.9f,
            1.0f, 0.9f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        shrinkAnimation.duration = 200
        shrinkAnimation.fillAfter = true

        val restoreAnimation = ScaleAnimation(
            0.9f, 1.0f,
            0.9f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        restoreAnimation.duration = 200
        restoreAnimation.fillAfter = true

        binding.moonImg.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.moonImg.startAnimation(shrinkAnimation)
                }

                MotionEvent.ACTION_UP -> {
                    binding.moonImg.startAnimation(restoreAnimation)

                    val savedBirthdate = sharedPreferences.getString("birthdate", "")
                    val yearOfBirth = getYearOfBirthFromBirthdate(savedBirthdate)
                    val chineseZodiacAnimal = getChineseZodiacAnimal(yearOfBirth)
                    val adviceList = viewModel.adviceMap[chineseZodiacAnimal]

                    if (!adviceList.isNullOrEmpty()) {
                        val randomAdvice = adviceList.random()
                        binding.tvMain.text = randomAdvice
                    } else {
                        binding.tvMain.text = getString(R.string.no_tips_available)
                    }
                }
            }
            true
        }
    }

    private fun getChineseZodiacAnimal(year: Int): String {
        val animals = arrayOf(
            getString(R.string.monkey),
            getString(R.string.rooster),
            getString(R.string.dog),
            getString(
                R.string.pig
            ),
            getString(R.string.rat),
            getString(R.string.ox),
            getString(
                R.string.tiger
            ),
            getString(R.string.rabbit),
            getString(R.string.dragon),
            getString(R.string.snake),
            getString(R.string.horse),
            getString(
                R.string.goat
            )
        )
        val startYear = 1900
        val animalIndex = (year - startYear) % 12
        return animals[animalIndex]
    }

    private fun getYearOfBirthFromBirthdate(birthdate: String?): Int {
        val birthdateComponents = birthdate?.split("-")
        return birthdateComponents?.getOrNull(0)?.toIntOrNull() ?: 0
    }
}