package es.rudo.rickandmortyapp.app.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterOriginRoom(
    @ColumnInfo(name = "character_origin_name")
    @PrimaryKey
    var name: String = "",

    @ColumnInfo(name = "character_origin_url")
    var url: String? = null
)

data class CharacterOrigin(
    var name: String = "",
    var url: String? = null
)

fun CharacterOriginRoom.toCharacterOrigin(): CharacterOrigin {
    return CharacterOrigin(name = this.name, url = this.url)
}

fun CharacterOrigin.toCharacterOriginRoom(): CharacterOriginRoom {
    return CharacterOriginRoom(name = this.name, url = this.url)
}
