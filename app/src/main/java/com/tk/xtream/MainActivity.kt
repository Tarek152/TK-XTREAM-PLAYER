package com.tk.xtream
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply { 
            orientation = LinearLayout.VERTICAL 
            setPadding(50, 50, 50, 50)
        }
        val urlInput = EditText(this).apply { hint = "Server URL (http://...)" }
        val userInput = EditText(this).apply { hint = "Username" }
        val passInput = EditText(this).apply { hint = "Password" }
        val btn = Button(this).apply { text = "LOGIN & PLAY" }
        val pv = PlayerView(this).apply { layoutParams = LinearLayout.LayoutParams(-1, 900) }
        
        layout.addView(urlInput); layout.addView(userInput); layout.addView(passInput); layout.addView(btn); layout.addView(pv)
        setContentView(layout)

        val player = ExoPlayer.Builder(this).build()
        pv.player = player

        btn.setOnClickListener {
            val link = "${urlInput.text}/live/${userInput.text}/${passInput.text}/1.ts"
            player.setMediaItem(MediaItem.fromUri(link))
            player.prepare()
            player.play()
            Toast.makeText(this, "Starting Stream...", Toast.LENGTH_LONG).show()
        }
    }
}
