import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

data class Feedback(val rating: Float, val count: Int)
data class Info(val title: String, val value: String)
data class Products(
    val title: String,
    val subtitle: String,
    val available: Int,
    val feedback: Feedback,
    val description: String,
    val info: List<Info>,
    val ingredients: String
)

class ProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val products = remember { getProducts() }
            ProductScreen(products)
        }
    }
}

@Composable
fun ProductScreen(products: List<Products>) {
    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(products) { product ->
                ProductItem(product)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ProductItem(product: Products) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Кнопка Поделиться
            IconButton(onClick = { /* Ничего не происходит */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_share), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            // Иконка добавления в избранное
            var isFavorite by remember { mutableStateOf(false) }
            IconButton(onClick = { isFavorite = !isFavorite }) {
                val favoriteIcon: Painter = if (isFavorite) {
                    painterResource(id = R.drawable.ic_favorite_filled)
                } else {
                    painterResource(id = R.drawable.ic_favorite_outline)
                }
                Icon(painter = favoriteIcon, contentDescription = null)
            }
            Spacer(modifier = Modifier.weight(1f))
            // Иконка вопроса
            IconButton(onClick = { /* Ничего не происходит */ }) {
                Icon(painter = painterResource(id = R.drawable.ic_question), contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = product.title, style = MaterialTheme.typography.headlineMedium)
        Text(text = product.subtitle, style = MaterialTheme.typography.subtitle1)
        Text(
            text = "Доступно для заказа ${product.available} ${if (product.available == 1) "штука" else "штуки"}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Рейтинг товара и количество отзывов
        if (product.feedback.rating > 0) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                StarRating(rating = product.feedback.rating)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${product.feedback.count} отзыва", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Новая цена
        Text(
            text = "${product.price} ${product.unit}",
            style = MaterialTheme.typography.headlineMedium
        )
        // Старая цена со скидкой
        Text(
            text = "${product.oldPrice} ${product.unit}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textDecoration = TextDecoration.LineThrough
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Описание товара
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Характеристики
        Text(
            text = "Характеристики",
            style = MaterialTheme.typography.headlineMedium
        )
        product.info.forEach { info ->
            Text(
                text = "${info.title}: ${info.value}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Состав товара
        Text(
            text = "Состав",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = product.ingredients,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun StarRating(rating: Float) {
    val fullStarCount = rating.toInt()
    val halfStar = rating - fullStarCount > 0.5
    val emptyStarCount = 5 - fullStarCount - if (halfStar) 1 else 0

    Row {
        repeat(fullStarCount) {
            Icon(painter = painterResource(id = R.drawable.ic_star_filled), contentDescription = null)
        }
        if (halfStar) {
            Icon(painter = painterResource(id = R.drawable.ic_star_half_filled), contentDescription = null)
        }
        repeat(emptyStarCount) {
            Icon(painter = painterResource(id = R.drawable.ic_star_outline), contentDescription = null)
        }
    }
}

fun getProducts(): List<Products> {
    // Возвращает список продуктов
}
