package es.rudo.rickandmortyapp.app.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CharacterRoom(
    @PrimaryKey
    var id: Int? = null,

    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: String? = null,

    @Embedded
    var characterOriginRoom: CharacterOriginRoom? = null,

    @Embedded
    var characterLocationRoom: CharacterLocationRoom? = null,

    var image: String? = null,

    var url: String? = null,
    var created: String? = null
)

data class Character(
    var id: Int? = null,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: String? = null,

    @SerializedName("origin")
    var characterOrigin: CharacterOrigin? = null,

    @SerializedName("location")
    var characterLocation: CharacterLocation? = null,

    var image: String? = null,
    var url: String? = null,
    var created: String? = null
)

fun CharacterRoom?.toCharacter(): Character {
    return Character(
        id = this?.id,
        name = this?.name,
        status = this?.status,
        species = this?.species,
        type = this?.type,
        gender = this?.gender,
        characterOrigin = CharacterOrigin(
            name = this?.characterOriginRoom?.name.toString(),
            url = this?.characterOriginRoom?.url
        ),
        characterLocation = CharacterLocation(
            name = this?.characterLocationRoom?.name.toString(),
            url = this?.characterLocationRoom?.url
        ),
        image = this?.image,
        url = this?.url,
        created = this?.created
    )
}

fun Character?.toCharacterRoom(): CharacterRoom {
    return CharacterRoom(
        id = this?.id,
        name = this?.name,
        status = this?.status,
        species = this?.species,
        type = this?.type,
        gender = this?.gender,
        characterOriginRoom = CharacterOriginRoom(
            name = this?.characterOrigin?.name.toString(),
            url = this?.characterOrigin?.url
        ),
        characterLocationRoom = CharacterLocationRoom(
            name = this?.characterLocation?.name.toString(),
            url = this?.characterLocation?.url
        ),
        image = this?.image,
        url = this?.url,
        created = this?.created
    )
}

fun List<Character>?.toRoomCharactersList(): List<CharacterRoom> {
    val mutableList = mutableListOf<CharacterRoom>()
    this?.let { charactersList ->
        for (item in charactersList) {
            mutableList.add(
                CharacterRoom(
                    id = item.id,
                    name = item.name,
                    status = item.status,
                    species = item.species,
                    type = item.type,
                    gender = item.gender,
                    characterOriginRoom = CharacterOriginRoom(
                        name = item.characterOrigin?.name.toString(),
                        url = item.characterOrigin?.url
                    ),
                    characterLocationRoom = CharacterLocationRoom(
                        name = item.characterLocation?.name.toString(),
                        url = item.characterLocation?.url
                    ),
                    image = item.image,
                    url = item.url,
                    created = item.created
                )
            )
        }
    }
    return mutableList.toList()
}

fun List<CharacterRoom>?.toCharactersList(): List<Character> {
    val mutableList = mutableListOf<Character>()
    this?.let { charactersList ->
        for (item in charactersList) {
            mutableList.add(
                Character(
                    id = item.id,
                    name = item.name,
                    status = item.status,
                    species = item.species,
                    type = item.type,
                    gender = item.gender,
                    characterOrigin = CharacterOrigin(
                        name = item.characterOriginRoom?.name.toString(),
                        url = item.characterOriginRoom?.url
                    ),
                    characterLocation = CharacterLocation(
                        name = item.characterLocationRoom?.name.toString(),
                        url = item.characterLocationRoom?.url
                    ),
                    image = item.image,
                    url = item.url,
                    created = item.created
                )
            )
        }
    }
    return mutableList.toList()
}
