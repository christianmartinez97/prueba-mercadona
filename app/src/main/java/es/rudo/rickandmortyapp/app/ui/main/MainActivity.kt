package es.rudo.rickandmortyapp.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.rudo.rickandmortyapp.app.R
import es.rudo.rickandmortyapp.app.data.models.Empty
import es.rudo.rickandmortyapp.app.data.models.Error
import es.rudo.rickandmortyapp.app.data.models.Success
import es.rudo.rickandmortyapp.app.databinding.ActivityMainBinding
import es.rudo.rickandmortyapp.app.helpers.Constants.BUNDLE_CHARACTER_ID
import es.rudo.rickandmortyapp.app.ui.character_detail.CharacterDetailActivity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setupToolbar()
        initListeners()
        initObservers()
        setupAdapter()
        viewModel.observeCharacters()
        viewModel.refreshCharacters()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun initListeners() {
        binding.buttonRefresh.setOnClickListener {
            viewModel.refreshCharacters()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.mainUiState
                .map { it.charactersList }
                .collect {
                    if (it.isNotEmpty()) {
                        adapter.submitList(it.toMutableList())
                    }
                }
        }
        lifecycleScope.launch {
            viewModel.mainUiState
                .map { it.success }
                .collect {
                    manageSuccess(it)
                }
        }
        lifecycleScope.launch {
            viewModel.mainUiState
                .map { it.error }
                .collect {
                    manageError(it)
                }
        }
    }

    private fun setupAdapter() {
        adapter = CharactersAdapter { character ->
            val intent = Intent(this, CharacterDetailActivity::class.java)
            intent.putExtra(BUNDLE_CHARACTER_ID, character.id)
            startActivity(intent)
        }
        binding.recyclerCharacters.adapter = adapter
    }

    private fun manageError(error: Error) {
        if (error is Empty) return
        Toast.makeText(this, "$error - ${error.description}", Toast.LENGTH_SHORT).show()
    }

    private fun manageSuccess(success: Success) {
        if (success.isSuccess) {
            Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
        }
    }
}
