package sugratech.com.klistexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import sugratech.com.klistexample.data.model.Coin
import sugratech.com.klistexample.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// HomeViewModel: Responsible for preparing and managing data for the HomeScreen.
// It interacts with the domain layer (use cases) to fetch data.
class HomeViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    // MutableStateFlows to hold the UI state.
    // StateFlow is a hot Flow that emits the current state value to its collectors.
    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins: StateFlow<List<Coin>> = _coins

    private val _isLoadingCoins = MutableStateFlow(false)
    val isLoadingCoins: StateFlow<Boolean> = _isLoadingCoins

    private val _errorCoins = MutableStateFlow<String?>(null)
    val errorCoins: StateFlow<String?> = _errorCoins


    // init block is called when the ViewModel is first created.
    init {
        fetchCoins()
    }

    // Function to fetch coins using the GetCoinsUseCase.
    // Launched in viewModelScope, so it's cancelled when the ViewModel is cleared.
    fun fetchCoins() {
        viewModelScope.launch {
            _isLoadingCoins.value = true
            _errorCoins.value = null
            try {
                _coins.value = getCoinsUseCase()
            } catch (e: Exception) {
                _errorCoins.value = e.message
            } finally {
                _isLoadingCoins.value = false
            }
        }
    }

    // Companion object to provide a custom ViewModelProvider.Factory.
    // This is necessary for ViewModels with constructor parameters (like use cases).
    companion object {
        fun provideFactory(
            getCoinsUseCase: GetCoinsUseCase,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Ensure the ViewModel being created is HomeViewModel.
                if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                    return HomeViewModel(getCoinsUseCase) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}