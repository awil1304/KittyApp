package de.awil1304.kittyapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.awil1304.kittyapp.common.Resource
import de.awil1304.kittyapp.domain.use_case.GetKittiesUseCase
import de.awil1304.kittyapp.presentation.kitty_list.KittyListState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class KittyListViewModel @Inject constructor(
    private val getKittiesUseCase: GetKittiesUseCase,
    // information about saved state for list order?
    // private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // state with default values - private because it shouldn't be modifiable in composables
    private val _state = mutableStateOf(KittyListState())
    // public one for separation of concerns
    val state: State<KittyListState> = _state

    init {
        getKitties()
        //savedStateHandel to get list index from clicked kitty object?
    }

    // provides data from getKittyUseCase and puts data in state object to display in ui
    private fun getKitties() {

        getKittiesUseCase().onEach { result ->
            when (result) {

                // when network request successful
                is Resource.Success -> {
                    // null so app doesn't crash in case of successful api request without objects
                    _state.value = KittyListState(kittyList = result.data ?: emptyList())
                }

                // when network request failed
                is Resource.Error -> {
                    _state.value = KittyListState(
                        error = result.message ?: "An unexpected error occurred."
                    )
                }

                // when network request is loading
                is Resource.Loading -> {
                    _state.value = KittyListState(
                        isLoading = true
                    )
                }
            }

            // flows have to be launched in coroutine scope because they are asynchronous
        }.launchIn(viewModelScope)
    }
}