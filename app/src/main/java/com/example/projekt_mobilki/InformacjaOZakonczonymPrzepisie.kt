package com.example.projekt_mobilki

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class InformacjaOZakonczonymPrzepisie: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.szczegoly_przepisu)

        val ViewModel = ViewModelProvider(this)[PrzepisViewModel::class.java]
        val tresc = findViewById<TextView>(R.id.przepisTEXT)
        val Nazwa = findViewById<TextView>(R.id.textView);
        val Zdjecie = findViewById<ImageView>(R.id.imageView);
        val Opis = findViewById<TextView>(R.id.textView4);
        val id = intent.getIntExtra("PRZEPIS_ID", -1)
        val typ = intent.getIntExtra("TYP", 0);
        val przepisViewModel = ViewModelProvider(this)[PrzepisViewModel::class.java]
        val usun = findViewById<Button>(R.id.usun)
        val dodaj = findViewById<Button>(R.id.dodajPrzepis);
        if (typ == 1)
        {
            usun.visibility = Button.VISIBLE
            dodaj.text = "Dostawaj powiadomienia";
        }
        usun.setOnClickListener {
            if (id != -1) {
                ViewModel.usunZActiveListy(id)
                val intent = Intent(this, ListaAktywnychPrzepisowActivity::class.java)
                startActivity(intent)

            }
        }
        dodaj.setOnClickListener{
            if (id!= -1)
            {
                if (typ ==0){
                    ViewModel.dodajAktywnyPrzepis(id);
                    val intent = Intent(this, ListaPrzepisowActivity::class.java)
                    startActivity(intent)

                }
                if (typ == 1)
                {
                    ViewModel.ustawNaZrobiony(id);
                }

            }
        }
        if (id != -1)
        {
            przepisViewModel.getById(id).observe(this, Observer{
                    przepis -> przepis?.let()
            {
                Nazwa.text = przepis.nazwa;
                Opis.text = przepis.opis;
                tresc.text = przepis.trescPrzepisu;

            }
            })
        }
    }
}