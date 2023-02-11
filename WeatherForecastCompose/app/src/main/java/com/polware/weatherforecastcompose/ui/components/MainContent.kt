package com.polware.weatherforecastcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.polware.weatherforecastcompose.R
import com.polware.weatherforecastcompose.data.models.ForecastList
import com.polware.weatherforecastcompose.data.models.WeatherResponse
import com.polware.weatherforecastcompose.data.utils.formatDate
import com.polware.weatherforecastcompose.data.utils.formatDateTime
import com.polware.weatherforecastcompose.data.utils.formatDecimals
import com.polware.weatherforecastcompose.ui.navigation.ScreenRoutes

@Composable
fun MainContent(weatherData: WeatherResponse, navController: NavController, metricStatus: Boolean) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherData.list[0].weather[0].icon}.png"
    val measurementUnit = if (metricStatus) "°C" else "°F"
    val speedUnit = if (metricStatus) "km/h" else "mp/h"

    Scaffold(
        topBar = {
            MainAppBar(title = weatherData.city.name +", ${weatherData.city.country}",
                backgroundColor = Color.Transparent,
                navController = navController,
                onSearchClick = {
                    navController.navigate(ScreenRoutes.SearchScreen.name)
                },
                elevation = 4.dp
            ) {

            }
        }
    ) {

        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = formatDate(weatherData.list[0].dt),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSecondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(6.dp)
            )
            Surface(modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
                shape = CircleShape,
                color = Color(0xFFb3cde0)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(model = imageUrl,
                        contentDescription = "Icon",
                        modifier = Modifier.size(70.dp)
                    )
                    Text(text = formatDecimals(weatherData.list[0].temp.day) + measurementUnit,
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(text = weatherData.list[0].weather[0].main,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            // Row for Humidity, pressure and wind
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.humidity),
                        contentDescription = "Humidity icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = " ${weatherData.list[0].humidity}%",
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                Row() {
                    Icon(painter = painterResource(id = R.drawable.pressure),
                        contentDescription = "Pressure icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = " ${weatherData.list[0].pressure} psi",
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                Row() {
                    Icon(painter = painterResource(id = R.drawable.wind),
                        contentDescription = "Wind icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = " ${weatherData.list[0].speed} " + speedUnit,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
            // Row for sunset & sunrise fields
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Icon(painter = painterResource(id = R.drawable.sunrise),
                        contentDescription = "Sunrise icon",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(text = formatDateTime(weatherData.list[0].sunrise),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                Row {
                    Icon(painter = painterResource(id = R.drawable.sunset),
                        contentDescription = "Sunset icon",
                        modifier = Modifier.size(30.dp)
                    )
                    Text(text = formatDateTime(weatherData.list[0].sunset),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
            Divider()
            Spacer(modifier = Modifier.height(15.dp))
            // Weekly weather forecast
            Text(text = "Weather forecast for week:",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color(0xFFEEF1EF),
                shape = RoundedCornerShape(size = 14.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(2.dp),
                    contentPadding = PaddingValues(1.dp)
                ) {
                    items(items = weatherData.list) {
                        item: ForecastList ->
                        WeekForecastItem(weatherItem = item)
                    }
                }
            }
            // End
        }
    }
}

