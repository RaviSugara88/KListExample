package sugratech.com.klistexample.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class KListConfig<T>(
    val modifier: Modifier = Modifier,
    val headerTitle: String? = null,
    val innerPadding: PaddingValues?=null,
    val items: List<T> = emptyList(),
    var onItemClick: ((T) -> Unit)? = null
) {
    fun padding(dp: Dp): KListConfig<T> = copy(modifier = modifier.padding(dp))
    fun header(title: String): KListConfig<T> = copy(headerTitle = title)
    fun innerPadding(padding: PaddingValues): KListConfig<T> = copy(innerPadding = padding)
    fun withItems(newItems: List<T>): KListConfig<T> = copy(items = newItems)
    fun clickable(onClick: (T) -> Unit): KListConfig<T> = copy (onItemClick = onClick)

}

@Composable
fun <T> KList(
    config: KListConfig<T>,
    itemContent: @Composable (T) -> Unit
) {
    Column(modifier = Modifier.padding(config.innerPadding?: PaddingValues(10.dp))) {
        config.headerTitle?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Spacer(Modifier.height(10.dp))
        LazyColumn(modifier = config.modifier
        ) {
            items(config.items) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { config.onItemClick?.invoke(item) }
                ) {
                    itemContent(item)
                }
            }
        }
    }

}
