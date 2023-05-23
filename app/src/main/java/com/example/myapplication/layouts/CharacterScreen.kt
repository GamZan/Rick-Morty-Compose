package com.example.myapplication.layouts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.myapplication.component.CharacterItems
import com.example.myapplication.component.RickMortyAppBar
import com.example.myapplication.data.model.CharacterFromRickAndMorty
import com.example.myapplication.data.paging.CharacterPagingSource

@Composable
fun CharacterScreen(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier.fillMaxSize(),
    onCharSelect: (Int) -> Unit
) {

    var topAppBarSize by remember { mutableStateOf(0) }

    Surface {
        Box(
            modifier = modifier
        ) {
            val characterPaging = remember {
                CharacterPagingSource()
            }
            val pager = remember {
              Pager(
                  PagingConfig(
                      pageSize = 10,
                      enablePlaceholders = true
                  )
              ) {
                  characterPaging.getCharacters()
              }
            }
            val lazyPagingItems: LazyPagingItems<CharacterFromRickAndMorty> = pager.flow.collectAsLazyPagingItems()

            LazyColumn(modifier = Modifier.padding(top = 80.dp)) {
                if(lazyPagingItems.loadState.refresh == LoadState.Loading) {
                    item {
                        Column(
                            modifier = modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()

                        }
                    }
                }

                itemsIndexed(lazyPagingItems) { _, item ->
                    if (item != null) {
                        CharacterItems(character = item, onClick = onCharSelect)
                    }
                }

                if (lazyPagingItems.loadState.append == LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

            RickMortyAppBar(title = {
                Text(text = "Rick & Morty")
            },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { topAppBarSize = it.height }
            )
        }
    }
}