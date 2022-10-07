package com.example.lab11pm.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter (
    private val characters: MutableList<Character>,
    private val listener: ViewHolder.RecyclerViewCharacterEvents
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(
        private val view: View,
        private val listener: RecyclerViewCharacterEvents
    ) : RecyclerView.ViewHolder(view) {



    }

}

