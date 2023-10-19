package cuttib.itmox.chinesezodiac.presentation.start_fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.FragmentEnterDataBinding
import java.util.Calendar

class EnterDataFragment : Fragment() {

    private var _binding: FragmentEnterDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences =
            requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("name", "")
        val savedBirthdate = sharedPreferences.getString("birthdate", "")

        if (!savedName.isNullOrBlank() && !savedBirthdate.isNullOrBlank()) {
            val args = Bundle()
            args.putString("name", savedName)
            args.putString("birthdate", savedBirthdate)
            startAct()
        }

        binding.next.setOnClickListener {
            val name = binding.etName.text.toString()
            val birthdate = binding.etDate.text.toString()

            if (name.isEmpty() || birthdate.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.fill_in_all_the_fields),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.putString("birthdate", birthdate)
                editor.apply()

                startAct()
            }
        }

        binding.etDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate =
                    String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                binding.etDate.setText(formattedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun startAct() {
        findNavController().navigate(R.id.action_enterDataFragment_to_moreInfoFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
