package sugratech.com.klistexample.domain.usecase

import sugratech.com.klistexample.data.model.Coin
import sugratech.com.klistexample.data.repository.CoinRepository

// Use case responsible for getting the list of coins.
// It depends on the CoinRepository interface, adhering to dependency inversion principle.
class GetCoinsUseCase(private val coinRepository: CoinRepository) {
    // The `invoke` operator function allows calling the use case like a function (e.g., `getCoinsUseCase()`).
    suspend operator fun invoke(): List<Coin> {
        return coinRepository.getCoins()
    }
}