package sugratech.com.klistexample.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sugratech.com.klistexample.data.model.Coin

// KListItem Composable: Renders a single cryptocurrency coin item.
// It uses Material Card for elevation and structure, and arranges content with Row and Column.
@Composable
fun KListItem(coin: Coin) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Card fills the width of its parent.
            .padding(horizontal = 8.dp, vertical = 4.dp), // Padding around the card itself.
       // elevation = 4.dp // Adds a shadow effect to the card.
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFF2F2F2)) // Light grey background for the row.
                .padding(12.dp) // Internal padding within the row.
                .fillMaxWidth(), // Row fills the card's width.
            verticalAlignment = Alignment.CenterVertically // Vertically centers items in the row.
        ) {

            Column(modifier = Modifier.weight(1f)) { // Column takes remaining space.
                // Coin symbol and name.
                Text(text = "${coin.symbol} - ${coin.name}")
                // Coin price, with a darker gray color.
                Text(text = "₹${"%.2f".format(coin.price)}", color = Color.DarkGray)
            }

            // Coin volume, formatted for readability (e.g., 120L for 1.2 Crore).
            Text(text = "Vol: ₹${"%.0f".format(coin.volume / 1_00_000)}L")
        }
    }
}