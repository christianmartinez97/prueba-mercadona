package es.rudo.rickandmortyapp.app.ui.character_detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import es.rudo.rickandmortyapp.app.R
import es.rudo.rickandmortyapp.app.data.models.Character
import es.rudo.rickandmortyapp.app.databinding.ActivityCharacterDetailBinding
import es.rudo.rickandmortyapp.app.helpers.Constants.BUNDLE_CHARACTER_ID
import es.rudo.rickandmortyapp.app.helpers.Constants.CHARACTER_UNKNOWN
import es.rudo.rickandmortyapp.app.helpers.Utils.setCharacterStatus
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)
        binding.lifecycleOwner = this

        initObservers()
        getExtras()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.characterDetailUIState
                .map { it.character }
                .collect {
                    setViewInfo(it)
                }
        }
    }

    private fun getExtras() {
        intent.extras?.getInt(BUNDLE_CHARACTER_ID)?.let {
            viewModel.setCharacterId(it)
            viewModel.getCharacterInfo()
        }
    }

    private fun setupToolbar(character: Character) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        Glide.with(this).load(character.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(GenericTransitionOptions.with(com.bumptech.glide.R.anim.abc_fade_in))
            .into(binding.imageMain)

        binding.toolbarTitle.text = character.name ?: CHARACTER_UNKNOWN
        binding.toolbar.navigationIcon?.setTint(
            ContextCompat.getColor(this, R.color.black)
        )
    }

    private fun setViewInfo(character: Character) {
        setupToolbar(character)
        setupInfo(character)
    }

    private fun setupInfo(characterRoom: Character) {
        binding.textStatusTitle.text = getString(R.string.character_status)
        setCharacterStatus(
            characterRoom.status,
            binding.imageMain,
            binding.textStatusDescription
        )

        binding.textSpecie.text = characterRoom.species.let {
            binding.textSpecie.setTextColor(ContextCompat.getColor(this, R.color.black))
            getString(R.string.character_specie, it)
        }

        binding.textGender.text = characterRoom.gender.let {
            binding.textSpecie.setTextColor(ContextCompat.getColor(this, R.color.black))
            getString(R.string.character_gender, it)
        }

        binding.textOrigin.text = characterRoom.characterOrigin.name.let {
            binding.textSpecie.setTextColor(ContextCompat.getColor(this, R.color.black))
            getString(R.string.character_origin, it)
        }

        binding.textLocation.text =
            characterRoom.characterLocation.name.let {
                binding.textSpecie.setTextColor(ContextCompat.getColor(this, R.color.black))
                getString(R.string.character_location, it)
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
