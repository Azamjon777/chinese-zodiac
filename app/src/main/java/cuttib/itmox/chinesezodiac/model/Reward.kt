package cuttib.itmox.chinesezodiac.model

import java.io.Serializable

data class Reward(
    val title: String,
    val img: Int,
    var isWin: Boolean
) : Serializable
