package cuttib.itmox.chinesezodiac.presentation.main_fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cuttib.itmox.chinesezodiac.adapter.RewardAdapter
import cuttib.itmox.chinesezodiac.databinding.FragmentRewardsBinding
import cuttib.itmox.chinesezodiac.model.Reward
import cuttib.itmox.chinesezodiac.presentation.GameActivity
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModel
import cuttib.itmox.chinesezodiac.viewmodel.ZodiacViewModelFactory

class RewardsFragment : Fragment(), RewardAdapter.RewardClickListener {
    private lateinit var binding: FragmentRewardsBinding
    private lateinit var myAdapter: RewardAdapter
    private lateinit var viewModel: ZodiacViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ZodiacViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, factory)[ZodiacViewModel::class.java]

        val recyclerView = binding.gameRecyclerView
        myAdapter = RewardAdapter(this)
        recyclerView.adapter = myAdapter

        val sharedPreferences =
            requireContext().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        val rewardsJson = sharedPreferences.getString("rewards_list", null)

        val rewardsList = if (!rewardsJson.isNullOrEmpty()) {
            val type = object : TypeToken<List<Reward>>() {}.type
            Gson().fromJson(rewardsJson, type)
        } else {
            viewModel.rewards
        }

        myAdapter.submitList(rewardsList)
    }

    override fun onRewardClick(item: Reward) {
        val intent = Intent(requireActivity(), GameActivity::class.java)
        intent.putExtra("item", item)
        startActivityForResult(intent, REQUEST_CODE_GAME_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_GAME_ACTIVITY && resultCode == Activity.RESULT_OK) {
            val updatedItem = data?.getSerializableExtra("updatedItem") as? Reward
            if (updatedItem != null) {
                saveReward(updatedItem)
                val updatedRewards = getRewardsListFromSharedPreferences()
                myAdapter.submitList(updatedRewards)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getRewardsListFromSharedPreferences(): List<Reward> {
        val sharedPreferences =
            requireContext().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        val rewardsJson = sharedPreferences.getString("rewards_list", null)

        return if (!rewardsJson.isNullOrEmpty()) {
            val type = object : TypeToken<List<Reward>>() {}.type
            Gson().fromJson(rewardsJson, type)
        } else {
            viewModel.rewards
        }
    }

    private fun saveReward(updatedReward: Reward) {
        val sharedPreferences =
            requireContext().getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val rewardsJson = sharedPreferences.getString("rewards_list", null)
        val rewardsList = if (!rewardsJson.isNullOrEmpty()) {
            val type = object : TypeToken<List<Reward>>() {}.type
            Gson().fromJson(rewardsJson, type)
        } else {
            viewModel.rewards
        }

        val updatedRewards = rewardsList.map { reward ->
            if (reward.title == updatedReward.title) {
                updatedReward
            } else {
                reward
            }
        }

        val updatedRewardsJson = Gson().toJson(updatedRewards)
        editor.putString("rewards_list", updatedRewardsJson)
        editor.apply()
    }

    companion object {
        private const val REQUEST_CODE_GAME_ACTIVITY =
            123
    }
}