package cuttib.itmox.chinesezodiac.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.databinding.RewardItemBinding
import cuttib.itmox.chinesezodiac.model.Reward

class RewardAdapter(
    private val rewardClickListener: RewardClickListener
) : RecyclerView.Adapter<RewardAdapter.RewardViewHolder>() {

    private val rewards = mutableListOf<Reward>()

    inner class RewardViewHolder(val binding: RewardItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val binding = RewardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RewardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        val reward = rewards[position]
        holder.binding.textRewardTitle.text = reward.title
        holder.binding.imageView.setImageResource(reward.img)

        val backgroundResource =
            if (reward.isWin) R.drawable.item_back_blue else R.drawable.item_back_yellow
        holder.binding.root.setBackgroundResource(backgroundResource)

        holder.binding.root.setOnClickListener {
            rewardClickListener.onRewardClick(reward)
        }
    }

    override fun getItemCount(): Int {
        return rewards.size
    }

    fun submitList(newRewards: List<Reward>) {
        rewards.clear()
        rewards.addAll(newRewards)
        notifyDataSetChanged()
    }

    interface RewardClickListener {
        fun onRewardClick(reward: Reward)
    }
}
