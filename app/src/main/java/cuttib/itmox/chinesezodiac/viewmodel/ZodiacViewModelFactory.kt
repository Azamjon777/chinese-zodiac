package cuttib.itmox.chinesezodiac.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ZodiacViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ZodiacViewModel::class.java)) {
            return ZodiacViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
