package cuttib.itmox.chinesezodiac.presentation.main_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.FragmentHomeBinding
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModel
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: ZodiacViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "")
        val savedBirthdate = sharedPreferences.getString("birthdate", "")

        binding.name.text = savedName

        val factory = ZodiacViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, factory)[ZodiacViewModel::class.java]

        if (!savedName.isNullOrBlank() && !savedBirthdate.isNullOrBlank()) {
            val zodiacSign = viewModel.getChineseZodiacSign(savedBirthdate)
            val zodiacIndex = viewModel.zodiacSigns.indexOf(zodiacSign)

            if (zodiacIndex != -1 && zodiacIndex < viewModel.zodiacDescriptions.size) {
                val description = viewModel.zodiacDescriptions[zodiacIndex]
                val message =
                    getString(R.string.you_were_born_in_the_year) + " "
                zodiacSign +
                        getString(R.string.according_to_the_chinese_calendar) + " "
                description
                binding.zodiacDescriptionTextView.text = message
            }
        }

        val birthdateComponents = savedBirthdate?.split("-")

        if (birthdateComponents?.size == 3) {
            val birthYear = birthdateComponents[0].toInt()

            val imageName = when (viewModel.getChineseZodiacAnimalYear(birthYear.toString())) {
                "Rat" -> "rat"
                "Ox" -> "ox"
                "Tiger" -> "tiger"
                "Rabbit" -> "rabbit"
                "Dragon" -> "dragon"
                "Snake" -> "snake"
                "Horse" -> "horse"
                "Sheep" -> "sheep"
                "Monkey" -> "monkey"
                "Rooster" -> "rooster"
                "Dog" -> "dog"
                "Pig" -> "pig"
                else -> null
            }

            if (imageName != null) {
                val imageResource =
                    resources.getIdentifier(imageName, "drawable", requireContext().packageName)
                binding.animalImageView.setImageResource(imageResource)
            }

            val birthMonth = birthdateComponents[1].toInt()
            val advice = viewModel.getAdviceForMonth(birthMonth - 1)
            binding.adviceTextView.text = advice
        } else {
            binding.animalImageView.setImageResource(R.drawable.ic_no_img)
            binding.adviceTextView.text = getString(R.string.no_advice)
        }
    }
}