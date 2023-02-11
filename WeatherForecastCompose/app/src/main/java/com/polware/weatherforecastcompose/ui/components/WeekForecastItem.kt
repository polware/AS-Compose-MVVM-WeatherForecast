package com.polware.weatherforecastcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.polware.weatherforecastcompose.data.models.ForecastList
import com.polware.weatherforecastcompose.data.utils.formatDate
import com.polware.weatherforecastcompose.data.utils.formatDecimals

@Composable
fun WeekForecastItem(weatherItem: ForecastList) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(8.dp)),
        color = Color(0xFFb3cde0)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDate(weatherItem.dt).split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            AsyncImage(model = imageUrl,
                contentDescription = "Icon",
                modifier = Modifier.size(70.dp)
            )
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weatherItem.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(formatDecimals(weatherItem.temp.max) + "°")
                    }
                    withStyle(style = SpanStyle(
                        color = Color.Black
                        )
                    ) {
                        append(formatDecimals(weatherItem.temp.min) + "°")
                    }
                }
            )
        }
    }
}