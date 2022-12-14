package com.example.lab11pm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.lab11pm.R
import com.example.lab11pm.datasource.model.Character

class CharacterAdapter(
    private val dataSet: MutableList<Character>,
    private val listener: RecyclerViewCharactersEvents
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(
        private val view: View,
        private val listener: RecyclerViewCharactersEvents
    ) : RecyclerView.ViewHolder(view) {
        private val layoutCharacter: ConstraintLayout =
            view.findViewById(R.id.layout_itemCharacter)
        private val imageCharacter: ImageView =
            view.findViewById(R.id.image_recycler_item_character)
        private val textName: TextView =
            view.findViewById(R.id.characterName_recycler_item_character)
        private val textSpecies: TextView =
            view.findViewById(R.id.characterSpecies_recycler_item_character)
        private val textStatus: TextView =
            view.findViewById(R.id.characterStatus_recycler_item_character)

        fun setData(character: Character) {
            character.apply {
                imageCharacter.load(character.image) {
                    placeholder(R.drawable.ic_downloading)
                    transformations(CircleCropTransformation())
                    error(R.drawable.ic_error)
                    memoryCachePolicy(CachePolicy.DISABLED)
                }
                textName.text = name
                textSpecies.text = species
                textStatus.text = status
            }
            layoutCharacter.setOnClickListener {
                listener.onItemClicked(character)
            }
        }
    }

    interface RecyclerViewCharactersEvents {
        fun onItemClicked(character: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_character, parent, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}



