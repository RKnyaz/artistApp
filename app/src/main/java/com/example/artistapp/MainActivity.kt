package com.example.artistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import com.example.artistapp.ui.theme.ArtistAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainLayer()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalWearMaterialApi::class)
@Composable
fun MainLayer() {
    var state by remember {
        mutableIntStateOf(0)
    }

    var txtMainMusic = stringResource(R.string.txt_main_ariya)
    var txtArtist = stringResource(R.string.group_txt_ariya)
    var year = "(2002)"
    var img = R.drawable.ariya

    when (state) {
        0 -> {
            txtArtist = stringResource(R.string.group_txt_ariya)
            txtMainMusic = stringResource(R.string.txt_main_ariya)
            year = stringResource(R.string.txt_year_ariya)
            img = R.drawable.ariya
        }

        1 -> {
            txtArtist = stringResource(R.string.group_txt_metallica)
            txtMainMusic = stringResource(R.string.txt_main_metallica)
            year = stringResource(R.string.txt_year_metallica)
            img = R.drawable.metallicaimg
        }

        else -> {
            txtArtist = stringResource(R.string.group_txt_korn)
            txtMainMusic = stringResource(R.string.txt_main_korn)
            year = stringResource(R.string.txt_year_korn)
            img = R.drawable.korn
        }
    }



    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
//            .padding(12.dp)
            .padding(top = 20.dp, bottom = 8.dp)
            .verticalScroll(state = ScrollState(0))
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier)
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(12.dp)
                .padding(top = 20.dp)
                .fillMaxWidth()
//                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .shadow(4.dp)
//                    .border(BorderStroke(20.dp, Color.Transparent))
                    .padding(20.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable { state = scrollNext(state) }
                    .fillMaxHeight()
//                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(4.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .align(Alignment.CenterHorizontally)
                    .width(600.dp)
            ) {
                Text(
                    text = txtMainMusic,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .combinedClickable(
                            onLongClick = { txtMainMusic = "0" }
                        ) { }
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                fontWeight = MaterialTheme.typography.bodySmall.fontWeight
                            )
                        )
                        {
                            append("$txtArtist  ")
                        }
                        withStyle(
                            SpanStyle(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                            )
                        ) {
                            append(year)
                        }
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 16.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(20.dp)
//                .weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(4.dp)
                .padding(top = 12.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()

        ) {
            Button(
                onClick = {
                    state = scrollPrevious(state)
                },
                Modifier
                    .size(height = 40.dp, width = 120.dp)
            ) {
                Text(text = stringResource(R.string.button_previous))

            }
            Button(
                onClick = {
                    state = scrollNext(state)
                },
                Modifier
//                        .weight(2f)
                    .size(height = 40.dp, width = 120.dp)
            ) {
                Text(text = stringResource(R.string.button_next))
            }
        }
    }

}

fun scrollNext(state: Int = 0): Int {
    return if (state < 2) state + 1
    else 0
}

fun scrollPrevious(state: Int = 0): Int {
    return if (state == 0) 2
    else state - 1
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtistAppTheme {
        MainLayer()
    }
}