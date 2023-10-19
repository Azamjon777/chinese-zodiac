package cuttib.itmox.chinesezodiac.presentation.start_fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.FragmentMoreInfoBinding
import cuttib.itmox.chinesezodiac.presentation.MainActivity
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModel
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModelFactory

class MoreInfoFragment : Fragment() {
    private lateinit var binding: FragmentMoreInfoBinding
    private lateinit var viewModel: ZodiacViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ZodiacViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, factory)[ZodiacViewModel::class.java]

        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "")
        val savedBirthdate = sharedPreferences.getString("birthdate", "")

        if (!savedName.isNullOrBlank() && !savedBirthdate.isNullOrBlank()) {
            val zodiacSign = viewModel.getChineseZodiacSign(savedBirthdate)
            val zodiacIndex = viewModel.zodiacSigns.indexOf(zodiacSign)

            if (zodiacIndex != -1 && zodiacIndex < viewModel.zodiacDescriptions.size) {
                val message = getString(R.string.you_were_born_in_the_year) + " " + zodiacSign
                binding.moreInfoTv.text = message
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
                binding.imgOfCalendar.setImageResource(imageResource)
            }
        } else {
            binding.imgOfCalendar.setImageResource(R.drawable.ic_no_img)
        }
        binding.continueBtn.setOnClickListener {
            startAct()
        }
    }

    private fun startAct() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        requireActivity().finish()
    }
}