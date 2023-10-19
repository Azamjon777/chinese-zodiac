package cuttib.itmox.chinesezodiac.presentation.start_fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.FragmentStartBinding
import cuttib.itmox.chinesezodiac.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "")
        val savedBirthdate = sharedPreferences.getString("birthdate", "")

        if (!savedName.isNullOrBlank() && !savedBirthdate.isNullOrBlank()) {
            startAct()
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                startMainActivity()
            }
        }
    }

    private fun startAct() {
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        requireActivity().finish()
    }

    private fun startMainActivity() {
        findNavController().navigate(R.id.action_startFragment_to_welcomeFragment)
    }
}