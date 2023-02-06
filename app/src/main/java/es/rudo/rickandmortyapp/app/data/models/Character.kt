package es.rudo.rickandmortyapp.app.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Characters")
data class CharacterRoom(
    @PrimaryKey
    var id: Int = 0,

    var name: String = "",
    var status: String = "",
    var species: String = "",
    var type: String = "",
    var gender: String = "",

    @Embedded
    var characterOriginRoom: CharacterOriginRoom = CharacterOriginRoom(),

    @Embedded
    var characterLocationRoom: CharacterLocationRoom = CharacterLocationRoom(),

    var image: String = "",

    var url: String = "",
    var created: String = ""
)

data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",

    @SerializedName("origin")
    val characterOrigin: CharacterOrigin = CharacterOrigin(),

    @SerializedName("location")
    val characterLocation: CharacterLocation = CharacterLocation(),

    val image: String = "",
    val url: String = "",
    val created: String = ""
)

fun CharacterRoom.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        characterOrigin = CharacterOrigin(
            name = this.characterOriginRoom.name,
            url = this.characterOriginRoom.url
        ),
        characterLocation = CharacterLocation(
            name = this.characterLocationRoom.name,
            url = this.characterLocationRoom.url
        ),
        image = this.image,
        url = this.url,
        created = this.created
    )
}

fun Character.toCharacterRoom(): CharacterRoom {
    return CharacterRoom(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        characterOriginRoom = CharacterOriginRoom(
            name = this.characterOrigin.name,
            url = this.characterOrigin.url
        ),
        characterLocationRoom = CharacterLocationRoom(
            name = this.characterLocation.name,
            url = this.characterLocation.url
        ),
        image = this.image,
        url = this.url,
        created = this.created
    )
}
