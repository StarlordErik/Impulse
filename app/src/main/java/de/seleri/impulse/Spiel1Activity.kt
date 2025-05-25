package de.seleri.impulse

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.impulse.R
import kotlin.random.Random

class Spiel1Activity : AppCompatActivity() {

    @Suppress("MaxLineLength")
    private val texts1 = listOf(
        "Do I look kind? Explain.",
        "What is my body language telling you right now?",
        "Do I seem like a coffee or tea person? Sweetened or unsweetened?",
        "Do you think I've ever been fired from a job? If so, what for?",
        "Wildcard: Close your eyes. What color are my eyes?",
        "What about me is most strange or unfamiliar to you?",
        "Do I seem like a morning person or a night owl? Why?",
        "Do you think I've ever checked an ex's phone for evidence?",
        "Do I seem like more of creative or analytical type? Explain.",
        "How likely am I to go camping? How high maintenance is my set up?",
        "Wildcard - both players: Write down something others would never guess about you just by looking at you. Compare.",
        "Wildcard - both players: Make an assumption about me.",
        "How many speeding tickets do you think I've gotten in my life?",
        "Wildcard: Draw a portrait of each other to the best of your ability. After 1 Minute, exchange.",
        "What do you think is the hardest part of what I do for a living?",
        "As a child, what do you think I wanted to be?",
        "On a scale of 1-10, how messy do you think my car is? 1 being cleanest, 10 a complete disaster. Explain.",
        "What subject do you think I thrived in at school? Did I fail any?",
        "If you were to buy me a present, knowing nothing about me other than what I look like, what would it be?",
        "What does my Instagram tell you about me?",
        "Wildcard - both players: Think of your favorite brand of cereal. On the count of three, say your answers out loud!",
        "Wildcard - both players: Think of your favorite childhood TV show of all time. On the count of three, say it out loud!",
        "Wildcard - both players: Ask and answer the next question in a different accent.",
        "Reminder: Let go of your attachment of the outcome.",
        "Do I seem like a cat or dog person?",
        "Wildcard: Maintain eye contact for thirty seconds. What did you notice?",
        "What do my shoes tell you about me?",
        "Wildcard - both players: Rate your dancing skills on a scale of 1-10. On the count of three, say your answers out loud!",
        "Wildcard: Draw a picture together. (30 seconds)",
        "What do you think my celebrity crush is?",
        "What fast food restaurant do you think I'm most likely to drive through? What's my order?",
        "What do you think my go to karaoke song is?",
        "Finish the sentence: just by looking at you I'd think ___.",
        "What reality show do you think I'm most likely to binge watch? Explain?",
        "Do you think plants thrive or die in my care. Explain.",
        "What about me intrigues you?",
        "Wildcard: Close your eyes. What color is my shirt?",
        "What do you think I'm most likely to splurge on?",
        "Do you think I was popular in school? Explain.",
        "What compliment do you think I hear the most?",
        "What does my phone wallpaper tell about me?",
        "Do you think I intimidate others? Why or why not?",
        "If Myspace were still a thing; what would my profile song be?",
        "What character would I play in a movie?",
        "Do you think I'm usually early, on time, or late to events? Explain.",
        "Do I seem like someone who would get a name tattooed on myself? Why or why not?",
        "What was your first impression of me?",
        "Do I remind you of anyone?",
        "Do you think I fall in love easily? Why or why not?",
        "What's the first thing you noticed about me?"
        )

    @Suppress("MaxLineLength")
    private val texts2 = listOf(
        "Describe your perfect day!",
        "Reminder: Be more interested in understanding others than being understood.",
        "Are you lying to yourself about anything?",
        "Is there a feeling you miss?",
        "What is a dream you've let go off?",
        "Wildcard: Admit something.",
        "Wildcard: Create a secret handshake!",
        "Have you ever told someone I love you but didn't mean it? If so, why?",
        "Are you missing anyone right now? Do you think they are missing you too?",
        "Wildcard: Staring contest. First to blink must reveal a personal problem and ask your partner for advice on how they might handle it.",
        "Wildcard: Both players write an embarrassing fun fact about yourselves. Play a game of rock, paper, scissors. Lost must reveal!",
        "What's your mother's name? And the most beautiful thing about her?",
        "Do you think the image you have of yourself matches the image people see you as?",
        "What´s your father´s name? And tell me one thing about him.",
        "If you have, when was the moment you realized you weren't invincible?",
        "How can you become a better person?",
        "If you could get to know someone in your life on a deeper level, who would it be and why?",
        "How would you describe the feeling of being in love in one word?",
        "What's the most pain you've ever been in that wasn't physical?",
        "What´s been your happiest memory this past year?",


    )

    @Suppress("MaxLineLength")
    private val texts3 = listOf(
        "Erster Text 3",
        "Zweiter Text 3",
        "Dritter Text 3"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_spiel1)

        val btnSpiel1 = findViewById<Button>(R.id.btnSpiel1)
        val btnSpiel2 = findViewById<Button>(R.id.btnSpiel2)
        val btnSpiel3 = findViewById<Button>(R.id.btnSpiel3)

        // Initiale zufällige Texte setzen
        btnSpiel1.text = texts1.random()
        btnSpiel2.text = texts2.random()
        btnSpiel3.text = texts3.random()

        // Klick-Listener für btnSpiel1
        btnSpiel1.setOnClickListener {
            val randomIndex = Random.nextInt(texts1.size)
            btnSpiel1.text = texts1[randomIndex]
        }

        // Klick-Listener für btnSpiel2
        btnSpiel2.setOnClickListener {
            val randomIndex = Random.nextInt(texts2.size)
            btnSpiel2.text = texts2[randomIndex]
        }

        // Klick-Listener für btnSpiel3
        btnSpiel3.setOnClickListener {
            val randomIndex = Random.nextInt(texts3.size)
            btnSpiel3.text = texts3[randomIndex]
        }
    }
}

