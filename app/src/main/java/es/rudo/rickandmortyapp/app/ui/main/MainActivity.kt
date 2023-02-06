package es.rudo.rickandmortyapp.app.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.rudo.rickandmortyapp.app.R
import es.rudo.rickandmortyapp.app.data.models.Empty
import es.rudo.rickandmortyapp.app.databinding.ActivityMainBinding
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
        initObservers()
        setupAdapter()
        viewModel.getAllCharacters()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
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
                .map { it.error }
                .collect {
                    manageError(it)
                }
        }
    }

    private fun setupAdapter() {
        adapter = CharactersAdapter({ character ->
            // TODO add navigation to character detail
        })
        binding.recyclerCharacters.adapter = adapter
    }

    private fun manageError(ex: Exception) {
        if (ex is Empty) return
        Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show()
    }
}
