package cuttib.itmox.chinesezodiac.presentation

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.ActivityGameBinding
import cuttib.itmox.chinesezodiac.model.Reward
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var hasCoin = false
    private var isWinShown = false
    private lateinit var receivedItem: Reward

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receivedItem = intent.getSerializableExtra("item") as Reward

        binding.boxImageView.setOnClickListener {
            if (!isWinShown) {
                openDoor()
            }
        }

        resetDoors()
    }

    private fun updateActivityWithUpdatedItem() {

        receivedItem.isWin = !receivedItem.isWin
        val intent = Intent()
        intent.putExtra("updatedItem", receivedItem)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun openDoor() {
        binding.boxImageView.isEnabled = false
        binding.boxImageView.setImageResource(R.drawable.box_opened)

        val randomNumber = Random.nextInt(1, 7)

        if (randomNumber == 1) {
            showCoinImage()
            isWinShown = true
        } else {
            showCongratulationsDialog()
        }

        lifecycleScope.launch {
            delay(2000)
            resetDoors()
        }
    }

    private fun resetDoors() {
        binding.boxImageView.setImageResource(R.drawable.box_closed)
        binding.boxImageView.isEnabled = true

        hasCoin = getRandomBoolean()
        hideCoinImage()
    }

    private fun showCongratulationsDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.losing))
            .setMessage(getString(R.string.try_again))
            .setPositiveButton(getString(R.string.restart)) { dialog: DialogInterface, _: Int ->
                resetGame()
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetGame() {
        isWinShown = false
        resetDoors()
    }

    private fun getRandomBoolean(): Boolean {
        return Random.nextDouble() < 0.5
    }

    private fun showCoinImage() {
        binding.coinImageView.setImageResource(receivedItem.img)
        binding.coinImageView.isVisible = true
        binding.tvWin.isVisible = true
        lifecycleScope.launch {
            delay(2000)
            updateActivityWithUpdatedItem()
        }
    }

    private fun hideCoinImage() {
        binding.coinImageView.isVisible = false
    }
}