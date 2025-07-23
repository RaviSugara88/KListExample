package sugratech.com.klistexample.data.repository.impl

import kotlinx.coroutines.delay
import sugratech.com.klistexample.data.model.Coin
import sugratech.com.klistexample.data.repository.CoinRepository

// Implementation of CoinRepository. In a real application, this would interact with a network API or database.
class CoinRepositoryImpl : CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        // Simulate a network delay.
        delay(1000L)
        // Return hardcoded sample data.
        return listOf(
            Coin("Bitcoin", "BTC", 65000.0, 1200000000.0),
            Coin("Ethereum", "ETH", 3200.0, 800000000.0),
            Coin("Ripple", "XRP", 0.5, 150000000.0),
            Coin("Cardano", "ADA", 0.45, 90000000.0),
            Coin("Solana", "SOL", 150.0, 250000000.0),
            Coin("Dogecoin", "DOGE", 0.15, 60000000.0),
            Coin("Litecoin", "LTC", 70.0, 30000000.0),
            Coin("Chainlink", "LINK", 18.0, 40000000.0),
            Coin("Polkadot", "DOT", 7.0, 50000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0),
            Coin("Shiba Inu", "SHIB", 0.000025, 20000000.0)
        )
    }
}
