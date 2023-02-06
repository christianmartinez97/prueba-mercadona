package es.rudo.rickandmortyapp.app.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterLocationRoom(
    @ColumnInfo(name = "character_location_name")
    @PrimaryKey
    var name: String = "",

    @ColumnInfo(name = "character_location_url")
    var url: String? = null
)

data class CharacterLocation(
    var name: String = "",
    var url: String? = null
)

fun CharacterLocationRoom.toCharacterLocation(): CharacterLocation {
    return CharacterLocation(name = this.name, url = this.url)
}

fun CharacterLocation.toCharacterLocationRoom(): CharacterLocationRoom {
    return CharacterLocationRoom(name = this.name, url = this.url)
}
