package com.example.myapplication.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.data.model.CharacterFromRickAndMorty

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItems(
    modifier: Modifier = Modifier,
    character: CharacterFromRickAndMorty,
    onClick: (Int) -> Unit,
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(character.id.toInt()) })
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                model = character.image,
                contentDescription = null
            )
            Column(Modifier.padding(8.dp)) {
                Text(character.name, style = MaterialTheme.typography.h5)
                Text(character.status)
                Text(character.species)
            }
        }
    }
}

@Composable
private fun ImageLoadingIndicator() {
    Column(
        modifier = Modifier.size(128.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}