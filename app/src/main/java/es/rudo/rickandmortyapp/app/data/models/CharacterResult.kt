package es.rudo.rickandmortyapp.app.data.models

import com.google.gson.annotations.SerializedName

data class CharacterResult(

    @SerializedName("info")
    var characterPager: CharacterPager? = null,

    var results: List<Character>? = null
)
