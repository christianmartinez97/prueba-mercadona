package es.rudo.rickandmortyapp.app.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import es.rudo.rickandmortyapp.app.R
import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.databinding.ItemCharacterBinding
import es.rudo.rickandmortyapp.app.helpers.Constants.CHARACTER_UNKNOWN
import es.rudo.rickandmortyapp.app.helpers.Utils

class CharactersAdapter(
    private val onCharacterClicked: ((character: Character) -> Unit)
) :
    ListAdapter<Character, CharactersAdapter.ViewHolder>(ListAdapterCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onCharacterClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Character,
            onCharacterClicked: ((character: Character) -> Unit)
        ) {
            binding.mainLayout.setOnClickListener {
                onCharacterClicked(item)
            }

            item.name.let { name ->
                binding.textCharacterName.text =
                    binding.mainLayout.context.getString(R.string.character_name, name)
            }

            item.characterOrigin.name.let { origin ->
                binding.textCharacterOrigin.text =
                    binding.mainLayout.context.getString(R.string.character_origin, origin)
            }

            Utils.setCharacterStatus(
                item.status,
                binding.imageCharacter,
                binding.textCharacterStatus
            )

            Glide.with(binding.root.context).load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(GenericTransitionOptions.with(com.bumptech.glide.R.anim.abc_fade_in))
                .into(binding.imageCharacter)

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ListAdapterCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
