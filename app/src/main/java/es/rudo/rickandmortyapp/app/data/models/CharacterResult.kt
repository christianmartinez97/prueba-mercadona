package es.rudo.rickandmortyapp.app.data.models

import com.google.gson.annotations.SerializedName

data class CharacterResult(

    @SerializedName("info")
    val characterPager: CharacterPager = CharacterPager(),

    val results: List<Character> = listOf()
)
