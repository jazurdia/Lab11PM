package com.example.lab11pm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab11pm.R
import com.example.lab11pm.datasource.api.RetrofitInstance
import com.example.lab11pm.ui.adapters.CharacterAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.example.lab11pm.datasource.model.Character
import com.example.lab11pm.datasource.model.CharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : Fragment() {

    private lateinit var characters: MutableList<Character>
    private lateinit var adapter: CharacterAdapter
    private lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerCharacters: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCharacters = view.findViewById(R.id.recycler_characters)
        toolbar = view.findViewById(R.id.toolbar_characterList)

        setToolbar()
        setListeners()
        getCharacters()


    }

    private fun setListeners() {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_az -> {
                    characters.sortBy { character -> character.name }
                    adapter.notifyDataSetChanged()
                    true
                }

                R.id.menu_item_za -> {
                    characters.sortByDescending { character -> character.name }
                    adapter.notifyDataSetChanged()
                    true
                }

                R.id.menu_item_logout -> {
                    logout()
                    true
                }

                else -> false
            }
        }
    }

    private fun getCharacters(){
        RetrofitInstance.api.getCharacters().enqueue(object : Callback<CharactersResponse> {
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful) {
                    characters = response.body()?.results as MutableList<Character>
                    adapter = CharacterAdapter(characters, this@CharacterListFragment)
                    recyclerCharacters.adapter = adapter
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }




}