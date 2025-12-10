package com.example.projekt_mobilki

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.greetings_page)

        val buttonNext = findViewById<Button>(R.id.about_page_button)
        val buttonPrzepis = findViewById<Button>(R.id.przepisy_button)
        val buttonZapisane = findViewById<Button>(R.id.zapisne)
        val buttonAktywne = findViewById<Button>(R.id.aktywne)

        buttonZapisane.setOnClickListener {
            val intent = Intent(this, ListaAktywnychPrzepisowActivity::class.java)
            startActivity(intent)
        }
        buttonAktywne.setOnClickListener {
            val intent = Intent(this, AboutPageActivity::class.java)
            startActivity(intent)
        }
        buttonNext.setOnClickListener {
            val intent = Intent(this, AboutPageActivity::class.java)
            startActivity(intent)
        }

        buttonPrzepis.setOnClickListener {
            val intent = Intent(this, ListaPrzepisowActivity::class.java)
            startActivity(intent)
        }
    }


}