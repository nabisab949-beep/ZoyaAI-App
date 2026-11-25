package com.zoya.ai

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // मुख्य काला बैकग्राउंड
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#050A14"))
            setPadding(40, 60, 40, 40)
            gravity = Gravity.CENTER_HORIZONTAL
        }

        // सबसे ऊपर ZOYA AI का टाइटल
        val titleText = TextView(this).apply {
            text = "Z O Y A   A I"
            textSize = 26f
            setTextColor(Color.parseColor("#00E5FF"))
            gravity = Gravity.CENTER
            letterSpacing = 0.2f
        }
        rootLayout.addView(titleText)

        // स्टेटस टेक्स्ट
        val statusText = TextView(this).apply {
            text = "SYSTEM ONLINE • READY"
            textSize = 12f
            setTextColor(Color.parseColor("#80D8FF"))
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 60)
        }
        rootLayout.addView(statusText)

        // Iron Man Arc Reactor जैसा चमकता नीला गोला
        val arcCore = View(this).apply {
            val shape = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.parseColor("#0A192F"))
                setStroke(14, Color.parseColor("#00E5FF"))
            }
            background = shape
        }
        val coreParams = LinearLayout.LayoutParams(450, 450).apply {
            gravity = Gravity.CENTER
            setMargins(0, 80, 0, 80)
        }
        rootLayout.addView(arcCore, coreParams)

        // गोले के धड़कने (Pulse) का एनिमेशन
        val pulseAnim = ObjectAnimator.ofPropertyValuesHolder(
            arcCore,
            PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.08f),
            PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.08f)
        ).apply {
            duration = 1200
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
        pulseAnim.start()

        // बीच की खाली जगह भरने के लिए स्पेसर
        val spacer = View(this).apply {
            layoutParams = LinearLayout.LayoutParams(0, 0, 1f)
        }
        rootLayout.addView(spacer)

        // नीचे चैट इनपुट और माइक बटन की पट्टी
        val bottomBar = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(20, 15, 20, 15)
            background = GradientDrawable().apply {
                setColor(Color.parseColor("#0D1B2A"))
                cornerRadius = 50f
                setStroke(3, Color.parseColor("#00B0FF"))
            }
        }

        val chatInput = EditText(this).apply {
            hint = "Zoya se kuch puchein..."
            setHintTextColor(Color.parseColor("#546E7A"))
            setTextColor(Color.WHITE)
            background = null
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }
        bottomBar.addView(chatInput)

        val micBtn = ImageButton(this).apply {
            setImageResource(android.R.drawable.ic_btn_speak_now)
            setBackgroundColor(Color.TRANSPARENT)
            setColorFilter(Color.parseColor("#00E5FF"))
            setOnClickListener {
                Toast.makeText(this@MainActivity, "ZOYA: Stage 1 UI Ready!", Toast.LENGTH_SHORT).show()
            }
        }
        bottomBar.addView(micBtn)

        rootLayout.addView(bottomBar)
        setContentView(rootLayout)
    }
}
