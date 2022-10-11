package com.example.lab11pm.ui.fragments
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.lab11pm.R
import com.example.lab11pm.datasource.api.RetrofitInstance
import com.example.lab11pm.datasource.model.Character
import com.google.android.material.appbar.MaterialToolbar


class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    private lateinit var textName: TextView
    private lateinit var textSpecies: TextView
    private lateinit var textGender: TextView
    private lateinit var textStatus: TextView
    private lateinit var textOrigin: TextView
    private lateinit var textEpisodes: TextView
    private lateinit var imageCharacter: ImageView
    private lateinit var toolbar: MaterialToolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            textName = findViewById(R.id.text_CharacterDetails_name)
            textSpecies = findViewById(R.id.text_CharacterDetails_specie)
            textGender = findViewById(R.id.text_CharacterDetails_gender)
            textStatus = findViewById(R.id.text_CharacterDetails_status)
            textOrigin = findViewById(R.id.text_characterDetails_Origin)
            textEpisodes = findViewById(R.id.text_CharacterDetails_Episodes)
            imageCharacter = findViewById(R.id.imageView_CharacterDetails_image)
            toolbar = findViewById(R.id.toolbar_characterDetails)
        }

        setToolbar()
        getCharacter()

    }

    private fun setToolbar(){
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appbarConfig)
    }

    private fun getCharacter(){
        RetrofitInstance.api.getCharacter(args.id).enqueue(object: Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful && response.body() != null){
                    setData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setData(character: Character) {
        character.apply {
            textName.text = name
            textSpecies.text = species
            textGender.text = gender
            textStatus.text = status
            textOrigin.text = origin.name
            textEpisodes.text = episode.size.toString()
            imageCharacter.load(image) {
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_foreground)
                memoryCachePolicy(CachePolicy.ENABLED)
            }
        }
    }


}


