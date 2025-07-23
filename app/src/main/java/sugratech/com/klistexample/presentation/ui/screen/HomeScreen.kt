package sugratech.com.klistexample.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import sugratech.com.klistexample.data.model.Coin
import sugratech.com.klistexample.data.repository.CoinRepository
import sugratech.com.klistexample.domain.usecase.GetCoinsUseCase
import sugratech.com.klistexample.presentation.ui.components.KList
import sugratech.com.klistexample.presentation.ui.components.KListConfig
import sugratech.com.klistexample.presentation.ui.components.KListItem
import sugratech.com.klistexample.presentation.viewmodel.HomeViewModel
import sugratech.com.klistexample.ui.theme.KListExampleTheme

// HomeScreen Composable: This is the main screen that uses the custom KList DSL.
// It receives its ViewModel as a parameter, making it testable and decoupled.
@Composable
fun HomeScreen(homeViewModel: HomeViewModel,innerPadding: PaddingValues) {
    // Observe the StateFlows from the ViewModel.
    // `collectAsState` converts a Flow into a State that can be read by Compose.
    val coins by homeViewModel.coins.collectAsState()
    val isLoadingCoins by homeViewModel.isLoadingCoins.collectAsState()
    val errorCoins by homeViewModel.errorCoins.collectAsState()
    val context = LocalContext.current


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
    ) {
        // Display loading indicators or error messages if data is not ready or failed.
        if (isLoadingCoins) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            Text("Loading Coins...")
        } else if (errorCoins != null) {
            Text("Error loading coins: ${errorCoins}", color = androidx.compose.ui.graphics.Color.Red)
        } else {
            val listConfig = KListConfig<Coin>()
                .innerPadding(innerPadding)
                .padding(10.dp)
                .header("Top Gainers")
                .withItems(coins)
                .clickable {
                    Toast.makeText(context, "${it.name}", Toast.LENGTH_SHORT).show()
                }
            KList(config = listConfig) { coins ->
                KListItem(coin = coins as Coin)
            }
        }


    }
}

// Preview function for HomeScreen.
// Note: For previews, you'd typically pass a mock ViewModel or mock data.
// Here, we create a simple mock for demonstration.
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KListExampleTheme {
        // Provide a dummy ViewModel for the preview.
        // In a real scenario, you might use a mockito mock or a dedicated FakeViewModel.
      val getCoinsUseCase = GetCoinsUseCase(object : CoinRepository {
            override suspend fun getCoins(): List<Coin> = listOf(
                Coin("MockCoin1", "MC1", 100.0, 1000.0),
                Coin("MockCoin2", "MC2", 200.0, 2000.0)
            )
        })

        HomeScreen(
            innerPadding = PaddingValues(10.dp),
            homeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    getCoinsUseCase
                )
            )
        )
    }
}
