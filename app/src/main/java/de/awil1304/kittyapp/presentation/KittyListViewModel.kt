package de.awil1304.kittyapp.presentation

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.awil1304.kittyapp.common.Resource
import de.awil1304.kittyapp.domain.use_case.GetKittiesUseCase
import de.awil1304.kittyapp.presentation.kitty_list.KittyListState
import de.awil1304.kittyapp.presentation.kitty_list.components.ErrorSnackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class KittyListViewModel @Inject constructor(
    private val getKittyUseCase: GetKittiesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(KittyListState())
    val state: State<KittyListState> = _state

    init {
        getKitties()
    }

    private fun getKitties() {
        getKittyUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = KittyListState(kitty = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = KittyListState(
                        error = result.message ?: "An unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = KittyListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}