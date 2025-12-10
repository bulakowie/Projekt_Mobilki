package com.example.projekt_mobilki

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider

class ListaAktywnychPrzepisowActivity : ComponentActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var container: LinearLayout
    private lateinit var przepisViewModel: AktywneViewModel
    private var allPrzepisy: List<Przepis> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.przepisy_page)

        container = findViewById(R.id.przepisyContainer)
        searchEditText = findViewById(R.id.searchEditText)
        przepisViewModel = ViewModelProvider(this)[AktywneViewModel::class.java]

        val przyciskSkladnik = findViewById<Button>(R.id.przyciskSkladnik)
        val przyciskKategoria = findViewById<Button>(R.id.przyciskKategoria)
        val powrot = findViewById<Button>(R.id.powrot)
        przepisViewModel.allPrzepisyAktywne().observe(this) { przepisy ->
            allPrzepisy = przepisy
            wypiszListePrzepisow(przepisy)
        }
        powrot.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        przyciskKategoria.setOnClickListener {
            wypiszKategorie()
        }

        przyciskSkladnik.setOnClickListener {
            wypiszSkladniki()
        }

        przepisViewModel.allPrzepisyAktywne().observe(this) { przepisy ->
            wypiszListePrzepisow(przepisy)
        }
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrujPrzepisy(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun filtrujPrzepisy(query: String) {
        val filtered = allPrzepisy.filter {
            it.nazwa.contains(query, ignoreCase = true)
        }
        wypiszListePrzepisow(filtered)
    }
    fun wypiszKategorie() {
        container.removeAllViews()
        przepisViewModel.allKategorie().observe(this) { kategorie ->
            for (kat in kategorie) {
                addCard(kat.nazwa, kat.id) { idKategorie ->
                    otworzPrzepisyKategoria(idKategorie)
                }
            }
        }
    }

    fun wypiszSkladniki() {
        container.removeAllViews()
        przepisViewModel.allSkladniki().observe(this) { skladniki ->
            for (sk in skladniki) {
                addCard(sk.nazwa, sk.id) { idSkladnika ->
                    otworzPrzepisySkladnik(idSkladnika)
                }
            }
        }
    }

    private fun wypiszListePrzepisow(przepisy: List<Przepis>) {
        container.removeAllViews()

        if (przepisy.isEmpty()) {
            val tv = TextView(this).apply {
                text = "Brak przepisów w bazie danych"
                textSize = 18f
                setPadding(16, 16, 16, 16)
            }
            container.addView(tv)
            return
        }

        for (przepis in przepisy) {
            addCard(przepis.nazwa, przepis.id) { idPrzepisu ->
                otworzSzczegolyPrzepisu(idPrzepisu)
            }
        }
    }

    private fun addCard(tekst: String, id: Int, onClick: (Int) -> Unit) {
        val card = CardView(this).apply {
            radius = 20f
            cardElevation = 10f
            useCompatPadding = true
        }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 32)
        card.layoutParams = params

        val textView = TextView(this).apply {
            text = tekst
            textSize = 18f
            setPadding(32, 32, 32, 32)
        }

        card.setOnClickListener {
            onClick(id)
        }

        card.addView(textView)
        container.addView(card)
    }
    private fun otworzSzczegolyPrzepisu(id: Int) {
        val intent = Intent(this, SzczegolyPrzepisuActivity::class.java)
        intent.putExtra("PRZEPIS_ID", id)
        intent.putExtra("TYP", 1)
        startActivity(intent)
    }
    private fun otworzPrzepisyKategoria(id: Int) {
        przepisViewModel.GetByKategorie(id).observe(this) { przepisy ->
            wypiszListePrzepisow(przepisy)
        }
    }

    private fun otworzPrzepisySkladnik(id: Int) {
        przepisViewModel.GetBySkladniki(id).observe(this) { przepisy ->
            wypiszListePrzepisow(przepisy)
        }
    }

}
