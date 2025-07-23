package sugratech.com.klistexample.data.repository

import sugratech.com.klistexample.data.model.Coin

// Interface for fetching cryptocurrency coin data.
interface CoinRepository {
    // Suspended function to get a list of coins asynchronously.
    suspend fun getCoins(): List<Coin>
}