package cuttib.itmox.chinesezodiac.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import cuttib.itmox.chinesezodiac.R
import cuttib.itmox.chinesezodiac.model.Reward

class ZodiacViewModel(private val context: Context) : ViewModel() {

    val rewards = listOf(
        Reward(context.getString(R.string.novice_seeker), R.drawable.img1, true),
        Reward(context.getString(R.string.lunar_apprentice), R.drawable.img2, true),
        Reward(context.getString(R.string.starry_explorer), R.drawable.img3, true),
        Reward(context.getString(R.string.zodiac_voyager), R.drawable.img4, true),
        Reward(context.getString(R.string.moonlit_wayfarer), R.drawable.img5, true),
        Reward(context.getString(R.string.celestial_student), R.drawable.img6, true),
        Reward(context.getString(R.string.mystic_apprentice), R.drawable.img7, true),
        Reward(context.getString(R.string.harmony_seeker), R.drawable.img8, true),
        Reward(context.getString(R.string.enlightened_inquirer), R.drawable.img9, true),
        Reward(context.getString(R.string.sage_in_training), R.drawable.img10, true),
        Reward(context.getString(R.string.cosmic_traveler), R.drawable.img11, true),
        Reward(context.getString(R.string.eastern_philosopher), R.drawable.img12, true),
        Reward(context.getString(R.string.moon_sage), R.drawable.img13, true),
        Reward(context.getString(R.string.starry_pathfinder), R.drawable.img14, true),
        Reward(context.getString(R.string.zen_master), R.drawable.img15, true),
        Reward(context.getString(R.string.emperor_of_enlightenment), R.drawable.img16, true),
    )

    fun getChineseZodiacSign(birthdate: String): String {
        val year = birthdate.split("-")[0].toInt()

        val zodiacSigns = listOf(
            "Rat",
            "Ox",
            "Tiger",
            "Rabbit",
            "Dragon",
            "Snake",
            "Horse",
            "Sheep",
            "Monkey",
            "Rooster",
            "Dog",
            "Pig"
        )

        val zodiacIndex = (year - 1900) % 12
        return zodiacSigns[zodiacIndex]
    }

    val zodiacSigns = listOf(
        "Rat",
        "Ox",
        "Tiger",
        "Rabbit",
        "Dragon",
        "Snake",
        "Horse",
        "Sheep",
        "Monkey",
        "Rooster",
        "Dog",
        "Pig"
    )

    val zodiacDescriptions = listOf(
        context.getString(R.string.desc1),
        context.getString(R.string.desc2),
        context.getString(R.string.desc3),
        context.getString(R.string.desc4),
        context.getString(R.string.desc5),
        context.getString(R.string.desc6),
        context.getString(R.string.desc7),
        context.getString(R.string.desc8),
        context.getString(R.string.desc9),
        context.getString(R.string.desc10),
        context.getString(R.string.desc11),
        context.getString(R.string.desc12)
    )

    fun getAdviceForMonth(month: Int): String {
        return when (month) {
            0 -> context.getString(R.string.advice_january)
            1 -> context.getString(R.string.advice_february)
            2 -> context.getString(R.string.advice_march)
            3 -> context.getString(R.string.advice_april)
            4 -> context.getString(R.string.advice_may)
            5 -> context.getString(R.string.advice_june)
            6 -> context.getString(R.string.advice_july)
            7 -> context.getString(R.string.advice_august)
            8 -> context.getString(R.string.advice_september)
            9 -> context.getString(R.string.advice_october)
            10 -> context.getString(R.string.advice_november)
            11 -> context.getString(R.string.advice_december)
            else -> context.getString(R.string.no_advice)
        }
    }

    fun getChineseZodiacAnimalYear(birthdate: String): String {
        val year = birthdate.split("-")[0].toInt()

        val zodiacAnimals = listOf(
            "Rat",
            "Ox",
            "Tiger",
            "Rabbit",
            "Dragon",
            "Snake",
            "Horse",
            "Goat",
            "Monkey",
            "Rooster",
            "Dog",
            "Pig"
        )

        val zodiacIndex = (year - 1900) % 12
        return zodiacAnimals[zodiacIndex]
    }

    val adviceMap = mapOf(
        context.getString(R.string.rat) to listOf(
            context.getString(R.string.an1_1),
            context.getString(R.string.an1_2),
            context.getString(R.string.an1_3),
            context.getString(R.string.an1_4),
            context.getString(R.string.an1_5),
            context.getString(R.string.an1_6),
            context.getString(R.string.an1_7),
            context.getString(R.string.an1_8),
            context.getString(R.string.an1_9),
            context.getString(R.string.an1_10)
        ),
        context.getString(R.string.ox) to listOf(
            context.getString(R.string.an2_1),
            context.getString(R.string.an2_2),
            context.getString(R.string.an2_3),
            context.getString(R.string.an2_4),
            context.getString(R.string.an2_5),
            context.getString(R.string.an2_6),
            context.getString(R.string.an2_7),
            context.getString(R.string.an2_8),
            context.getString(R.string.an2_9),
            context.getString(R.string.an2_10)
        ),
        context.getString(R.string.tiger) to listOf(
            context.getString(R.string.an3_1),
            context.getString(R.string.an3_2),
            context.getString(R.string.an3_3),
            context.getString(R.string.an3_4),
            context.getString(R.string.an3_5),
            context.getString(R.string.an3_6),
            context.getString(R.string.an3_7),
            context.getString(R.string.an3_8),
            context.getString(R.string.an3_9),
            context.getString(R.string.an3_10)
        ),
        context.getString(R.string.rabbit) to listOf(
            context.getString(R.string.an4_1),
            context.getString(R.string.an4_2),
            context.getString(R.string.an4_3),
            context.getString(R.string.an4_4),
            context.getString(R.string.an4_5),
            context.getString(R.string.an4_6),
            context.getString(R.string.an4_7),
            context.getString(R.string.an4_8),
            context.getString(R.string.an4_9),
            context.getString(R.string.an4_10)
        ),
        context.getString(R.string.dragon) to listOf(
            context.getString(R.string.an5_1),
            context.getString(R.string.an5_2),
            context.getString(R.string.an5_3),
            context.getString(R.string.an5_4),
            context.getString(R.string.an5_5),
            context.getString(R.string.an5_6),
            context.getString(R.string.an5_7),
            context.getString(R.string.an5_8),
            context.getString(R.string.an5_9),
            context.getString(R.string.an5_10)
        ),
        context.getString(R.string.snake) to listOf(
            context.getString(R.string.an6_1),
            context.getString(R.string.an6_2),
            context.getString(R.string.an6_3),
            context.getString(R.string.an6_4),
            context.getString(R.string.an6_5),
            context.getString(R.string.an6_6),
            context.getString(R.string.an6_7),
            context.getString(R.string.an6_8),
            context.getString(R.string.an6_9),
            context.getString(R.string.an6_10)
        ),
        context.getString(R.string.horse) to listOf(
            context.getString(R.string.an7_1),
            context.getString(R.string.an7_2),
            context.getString(R.string.an7_3),
            context.getString(R.string.an7_4),
            context.getString(R.string.an7_5),
            context.getString(R.string.an7_6),
            context.getString(R.string.an7_7),
            context.getString(R.string.an7_8),
            context.getString(R.string.an7_9),
            context.getString(R.string.an7_10)
        ),
        context.getString(R.string.goat) to listOf(
            context.getString(R.string.an8_1),
            context.getString(R.string.an8_2),
            context.getString(R.string.an8_3),
            context.getString(R.string.an8_4),
            context.getString(R.string.an8_5),
            context.getString(R.string.an8_6),
            context.getString(R.string.an8_7),
            context.getString(R.string.an8_8),
            context.getString(R.string.an8_9),
            context.getString(R.string.an8_10)
        ),
        context.getString(R.string.monkey) to listOf(
            context.getString(R.string.an9_1),
            context.getString(R.string.an9_2),
            context.getString(R.string.an9_3),
            context.getString(R.string.an9_4),
            context.getString(R.string.an9_5),
            context.getString(R.string.an9_6),
            context.getString(R.string.an9_7),
            context.getString(R.string.an9_8),
            context.getString(R.string.an9_9),
            context.getString(R.string.an9_10)
        ),
        context.getString(R.string.rooster) to listOf(
            context.getString(R.string.an10_1),
            context.getString(R.string.an10_2),
            context.getString(R.string.an10_3),
            context.getString(R.string.an10_4),
            context.getString(R.string.an10_5),
            context.getString(R.string.an10_6),
            context.getString(R.string.an10_7),
            context.getString(R.string.an10_8),
            context.getString(R.string.an10_9),
            context.getString(R.string.an10_10)
        ),
        context.getString(R.string.dog) to listOf(
            context.getString(R.string.an11_1),
            context.getString(R.string.an11_2),
            context.getString(R.string.an11_3),
            context.getString(R.string.an11_4),
            context.getString(R.string.an11_5),
            context.getString(R.string.an11_6),
            context.getString(R.string.an11_7),
            context.getString(R.string.an11_8),
            context.getString(R.string.an11_9),
            context.getString(R.string.an11_10)
        ),
        context.getString(R.string.pig) to listOf(
            context.getString(R.string.an12_1),
            context.getString(R.string.an12_2),
            context.getString(R.string.an12_3),
            context.getString(R.string.an12_4),
            context.getString(R.string.an12_5),
            context.getString(R.string.an12_6),
            context.getString(R.string.an12_7),
            context.getString(R.string.an12_8),
            context.getString(R.string.an12_9),
            context.getString(R.string.an12_10)
        )
    )
}
