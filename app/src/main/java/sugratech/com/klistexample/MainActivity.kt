package sugratech.com.klistexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import sugratech.com.klistexample.data.repository.impl.CoinRepositoryImpl
import sugratech.com.klistexample.domain.usecase.GetCoinsUseCase
import sugratech.com.klistexample.presentation.ui.screen.HomeScreen
import sugratech.com.klistexample.presentation.viewmodel.HomeViewModel
import sugratech.com.klistexample.ui.theme.KListExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // AndroidProjectTheme applies the defined theme to all Composables within it.
            KListExampleTheme {
                // A surface container using the 'background' color from the theme.
                Scaffold {innerPadding->
                    Surface(
                        modifier = Modifier.fillMaxSize(), // Fills the entire available space.
                        color = MaterialTheme.colorScheme.background // Uses the theme's background color.
                    ) {
                        // Manual dependency injection for the ViewModel.
                        // In a larger app, you'd use a DI framework like Hilt or Koin.
                        val coinRepository = CoinRepositoryImpl()
                        val getCoinsUseCase = GetCoinsUseCase(coinRepository)

                        // Create and provide the HomeViewModel to the HomeScreen.
                        // The `viewModel()` Composable function handles ViewModel lifecycle.
                        HomeScreen(
                            innerPadding = innerPadding,
                            homeViewModel = viewModel(
                                factory = HomeViewModel.provideFactory(
                                    getCoinsUseCase
                                )
                            )
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KListExampleTheme {
        Greeting("Android")
    }
}