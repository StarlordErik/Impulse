package de.seleri.impulse

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.impulse.R
import kotlin.random.Random

class Spiel1Activity : AppCompatActivity() {

    @Suppress("MaxLineLength")
    private val perception = listOf(
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
    private val connection = listOf(
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
        "What is your 1st love's name and the reason you fell in love with him/her?",
        "What do you crave more of?",
        "What would your younger self not believe about your life today?",
        "Wildcard - both players: Write the three most important things in life to you. After 30 seconds, compare.",
        "Wildcard - both players: Draw your current mood. Then compare.",
        "What is something you wouldn't want to change about yourself?",
        "Wildcard: Questions are an art form. Create your own question.",
        "What was the last time you surprised yourself?",
        "When you're asked how are you, hof often do you answer truthfully?",
        "Have you changed your mind about anything recently?",
        "How are you, really?",
        "Has a stranger ever changed your life?",
        "What's been the best compliment a stranger has ever given you?",
        "What is a compliment you wish you received more frequently?",
        "What title would you give this chapter in your life?",
        "Wildcard: Press shuffle on your music library. Explain the first song that comes up.",
        "What part of your life works? What part of your life hurts?",
        "Wildcard: Call someone you admire and tell them why you appreciate them! (put on speaker phone)",
        "Wildcard: Show your first photo in your camera roll. Explain.",
        "Wildcard: Swap seats with your partner.",
        "Wildcard: Sing the chorus of your favorite song of all time. Get into it!",
        "If you could have it your way: Who would you be with? Where would you be? & What would you be doing?",
        "What lesson took you the longest to unlearn?",
        "Finish the sentences: Strangers would describe me as ___. Only I know that I am ___.",
        "What is the most unexplainable thing that's ever happened to you?",
        "What is the last thing you lied to your mother about?",
        "What are you still trying to prove to yourself?",
        "What are you more afraid of, failure or success? And why?",
        "Wildcard - both players: Think of something you strongly dislike that most people love. On the count of three say it out loud!",
        "What questions are you trying to answer most in your life right now?",
        "What has been your earliest recollection of happiness?",
        "Wildcard: Ask a question you'd be too afraid to ask. Something you wouldn't dare to ask.",
        "Final Card of the deck: Each player write a message to the other. Fold and exchange. Open only once you two have parted."
    )

    @Suppress("MaxLineLength")
    private val reflection = listOf(
        "In one word, how would you describe our conversation?",
        "Wildcard: Give your partner a compliment you don't think the hear enough.",
        "Wildcard: Admit something.",
        "What do you recommend I let go of, if anything?",
        "How does one earn your vulnerability? Have I earned it? How can I earn more?",
        "What do you think my weakness is?",
        "What can I help you with?",
        "What about me most surprised you?",
        "Wildcard: Play a round of rock paper scissors. Winner can ask their partner anything. Loser must answer.",
        "Wildcard - both players: Write down one thing you want to let go of this year. Read out loud, then rip up together.",
        "Wildcard - both players: Write down a goal for this year. Fold & exchange. Hold each other accountable.",
        "If we were in a band, what would our name be?",
        "What do you think our most important similarity is?",
        "Wildcard: Give each other nicknames!",
        "Do you believe everyone has a calling? If so, do you think I've found mine?",
        "When this game is over, what will you remember about me?",
        "When in this game did you fell most connected to me?",
        "Wildcard - both players: Dare your partner to do something outside of their comfort zone in the next week.",
        "What question were you most afraid of to answer?",
        "Wildcard: Take a selfie together.",
        "Wildcard: Scroll through each other's Instagrams. Find the picture you feel best represents your partner's essence and comment why you chose that image.",
        "Based on what you learned about me, what book would you recommend I read?",
        "How would you describe me to a stranger.",
        "What do you admire most about me?",
        "What do you think I should know about myself that perhaps I'm unaware of?",
        "Why do you think we met?",
        "Wildcard - both players: Write a song about your partner in 30 seconds. Then sing it out loud. Get into it!",
        "What answer of mine made you light up?",
        "How do our personalities complement each other?",
        "What do you think my superpower is?",
        "What would be the perfect gift for me?",
        "What has this conversation taught you about yourself?",
        "What parts of yourself do you see in me?",
        "What do I need to hear right now?",
        "Wildcard - both players: Swap a song suggestion your partner may enjoy.",
        "Wildcard: Create your own question. Make it count.",
        "Based on what you learned about me, does my social media accurately reflect who I am? Why or why not?",
        "Wildcard: Both players share something you're most grateful for in this current moment.",
        "In one word, describe how you feel right now.",
        "Wildcard: Give your partner a hug. Not the crappy kind. A warm fluffy one.",
        "What is a lesson you will take away form our conversation?",
        "Based on what you know about me, do you have any Netflix recommendations?",
        "What about me is hardest for you to understand?",
        "What am I most qualified to give advice about?",
        "If you could prescribe me one thing to do for the rest of this month, what would it be and why?",
        "Wildcard: Both players write a note to your younger selves in 1 minute. Option to compare.",
        "What do you think my defining characteristic is?",
        "What do you think I fear the most?",
        "What can we create together?",
        "What would make you feel closer to me?"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_spiel1)

        val btnSpiel11 = findViewById<Button>(R.id.btnSpiel11)
        val btnSpiel12 = findViewById<Button>(R.id.btnSpiel12)
        val btnSpiel13 = findViewById<Button>(R.id.btnSpiel13)

        // Klick-Listener für btnSpiel1
        btnSpiel11.setOnClickListener {
            val randomIndex = Random.nextInt(perception.size)
            btnSpiel11.text = perception[randomIndex]
        }

        // Klick-Listener für btnSpiel2
        btnSpiel12.setOnClickListener {
            val randomIndex = Random.nextInt(connection.size)
            btnSpiel12.text = connection[randomIndex]
        }

        // Klick-Listener für btnSpiel3
        btnSpiel13.setOnClickListener {
            val randomIndex = Random.nextInt(reflection.size)
            btnSpiel13.text = reflection[randomIndex]
        }
    }
}
