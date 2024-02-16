package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


data class Product(
    val id: String,
    val title: String,
    val subtitle: String,
    val oldPrice: Float,
    val newPrice: Float,
    val unit: String,
    val discount: Int,
    val imageUrl: String,
    val isFavorite: Boolean,
    val rating: Float,
    val reviewCount: Int
)


val products = listOf<Product>(
    Product(
        id = "1",
        title = "Philips",
        subtitle = "Philips Триммер для бороды и усов QP2510/15, зеленый",
        oldPrice = 4429.0f,
        newPrice = 2669.0f,
        unit = "руб.",
        discount = 40,
        imageUrl = "https://ir.ozone.ru/s3/multimedia-1-0/wc1000/6917393256.jpg",
        isFavorite = false,
        rating = 4.5f,
        reviewCount = 10
    ),
    Product(
        id = "3",
        title = "Xiaomi",
        subtitle = "Xiaomi Redmi Note 10 Pro 6/128GB Onyx Gray",
        oldPrice = 29999.0f,
        newPrice = 26999.0f,
        unit = "руб.",
        discount = 10,
        imageUrl = "https://images.mospromportal.ru/upload/iblock/8c5/8c54a073b50a33c792e04b9f64aa41d0.jpg",
        isFavorite = false,
        rating = 4.6f,
        reviewCount = 20
    ),
    Product(
        id = "4",
        title = "Sony",
        subtitle = "Sony PlayStation 5",
        oldPrice = 57999.0f,
        newPrice = 53999.0f,
        unit = "руб.",
        discount = 7,
        imageUrl = "https://avatars.mds.yandex.net/get-mpic/1909649/img_id8198877255097480427.jpeg/9hq",
        isFavorite = false,
        rating = 4.9f,
        reviewCount = 25
    ),
    Product(
        id = "5",
        title = "Apple",
        subtitle = "Apple MacBook Air M1 13' 8GB/256GB Space Gray",
        oldPrice = 99990.0f,
        newPrice = 93990.0f,
        unit = "руб.",
        discount = 6,
        imageUrl = "https://avatars.mds.yandex.net/get-mpic/3973976/img_id1917536952276231457.jpeg/9hq",
        isFavorite = true,
        rating = 4.7f,
        reviewCount = 30
    ),
    Product(
        id = "6",
        title = "Nike",
        subtitle = "Nike Air Force 1 '07",
        oldPrice = 8999.0f,
        newPrice = 7999.0f,
        unit = "руб.",
        discount = 11,
        imageUrl = "https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/2f608007-e7c2-4a42-bb80-0c7f4260c18a/air-force-1-07-shoe-wvwk8G.jpg",
        isFavorite = true,
        rating = 4.8f,
        reviewCount = 40
    ),
    Product(
        id = "7",
        title = "Adidas",
        subtitle = "Adidas Ultraboost 21",
        oldPrice = 14999.0f,
        newPrice = 13499.0f,
        unit = "руб.",
        discount = 10,
        imageUrl = "https://assets.adidas.com/images/h_840,f_auto,q_auto:sensitive,fl_lossy/52f91ed0f60f409e9ff0ac6f01237bb8_9366/ultraboost-21-shoes.jpg",
        isFavorite = false,
        rating = 4.7f,
        reviewCount = 35
    ),
    Product(
        id = "8",
        title = "Casio",
        subtitle = "Casio G-Shock GA-2100-4AER",
        oldPrice = 11999.0f,
        newPrice = 10999.0f,
        unit = "руб.",
        discount = 8,
        imageUrl = "https://static-sl.insales.ru/images/products/1/7390/454352588/large_casio-g-shock-ga-2100-4aer.jpg",
        isFavorite = true,
        rating = 4.6f,
        reviewCount = 25
    ),
    Product(
        id = "9",
        title = "Amazon",
        subtitle = "Amazon Kindle Paperwhite 11th Gen 2021",
        oldPrice = 13999.0f,
        newPrice = 12499.0f,
        unit = "руб.",
        discount = 11,
        imageUrl = "https://avatars.mds.yandex.net/get-mpic/1608501/img_id3686986191203875960.jpeg/9hq",
        isFavorite = false,
        rating = 4.8f,
        reviewCount = 20
    ),
    Product(
        id = "10",
        title = "LG",
        subtitle = "LG OLED65CX9LA",
        oldPrice = 219999.0f,
        newPrice = 199999.0f,
        unit = "руб.",
        discount = 9,
        imageUrl = "https://avatars.mds.yandex.net/get-mpic/1861933/img_id8713226693530218238.jpeg/9hq",
        isFavorite = true,
        rating = 4.9f,
        reviewCount = 15
    )
)
class CatalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        CatalogScreen()
        }
    }
}

@Preview
@Composable
fun CatalogScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Каталог", modifier = Modifier.padding(vertical = 16.dp))

        products.forEach{product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(imageURL = product.imageUrl, contentDiscriptuon = "Image")
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = product.title)
            Text(text = product.subtitle)
            Text(
                text = "Старая цена: ${product.oldPrice} ${product.unit}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Новая цена: ${product.newPrice} ${product.unit}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Скидка: ${product.discount}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun GlideImage( modifier: Modifier = Modifier,
                imageURL: String,
                contentDiscriptuon: String,) {
    val density = LocalDensity.current.density
    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    Image(
        painter = rememberImagePainter(
            data = imageURL,
        ),
        contentDescription = contentDiscriptuon,
        modifier = Modifier.size(64.dp)
    )
}



