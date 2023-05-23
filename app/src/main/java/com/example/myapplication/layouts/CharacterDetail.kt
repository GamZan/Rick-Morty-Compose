@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.myapplication.layouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.component.RickMortyAppBar
import com.example.myapplication.data.model.DetailForCharacters
import com.example.myapplication.presentation.CharacterDetailViewModel
import com.example.myapplication.presentation.EpisodeViewModel

@Composable
fun CharacterDetailScreen(charId: Int) {
    val charactersViewModel = CharacterDetailViewModel()
    var topAppBarSize by remember { mutableStateOf(0) }

    DisposableEffect(charId) {
        charactersViewModel.getCharacter(charId)
        onDispose {
            charactersViewModel.charLoading = true
        }
    }



    Surface {
        Box(Modifier.fillMaxWidth()) {
            if (charactersViewModel.charLoading) {
                Column(
                    Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                val char = charactersViewModel.character
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .padding(top = 80.dp)
                ) {
                    CharacterHeader(char = char)
                    CharacterStat(statLabel = "Status", stat = char.status)
                    CharacterStat(statLabel = "Species", stat = char.species)
                    CharacterStat(statLabel = "Type", stat = char.type)
                    CharacterStat(statLabel = "Gender", stat = char.gender)
                    CharacterStat(statLabel = "Origin", stat = char.origin.name)
                    CharacterStat(statLabel = "Location", stat = char.location.name)
                    EpisodesList(episodes = char.episode)
                }
            }
            RickMortyAppBar(title = {
                Text("Rick & Morty")
            },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { topAppBarSize = it.height }
            )
        }
    }
}

@Composable
fun CharacterHeader(char: DetailForCharacters) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        GlideImage(model = char.image, contentDescription = null)
        Spacer(Modifier.weight(1f))
        Column(
            Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        ) {
            Text(char.name, style = MaterialTheme.typography.h3)
        }
        Spacer(Modifier.weight(0.5f))
    }
}

@Composable
fun CharacterStat(statLabel: String, stat: String) {
    Text(
        "$statLabel: ${stat.ifBlank { "Unknown" }}",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light)
    )
}

@Composable
fun EpisodesList(episodes: List<String>) {
    Text(text = "Episodes:", style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold))
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(episodes) { episode ->
            EpisodeDetail(episode = episode)
        }
    }
}

@Composable
fun EpisodeDetail(episode: String) {
    val episodeViewModel = EpisodeViewModel()

    DisposableEffect(episode) {
        episodeViewModel.getEpisode(episode)
        onDispose {
            episodeViewModel.episodeLoading = true
        }
    }
    Surface {
        Box {
            if (episodeViewModel.episodeLoading) {
                Column(
                    Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                val ep = episodeViewModel.episodes

                Row {
                    Column {
                        Text(text = ep.name, overflow = TextOverflow.Ellipsis, modifier = Modifier.fillMaxWidth(.5f), maxLines = 1)
                        Text(text = ep.episode)
                    }
                    Text(modifier = Modifier.fillMaxWidth(), text = ep.airDate, textAlign = TextAlign.End, softWrap = false)
                }
            }
        }
    }
}