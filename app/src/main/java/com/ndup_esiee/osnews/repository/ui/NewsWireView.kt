package com.ndup_esiee.osnews.repository.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ndup_esiee.osnews.repository.model.Multimedia
import com.ndup_esiee.osnews.repository.model.NewsWire
import com.ndup_esiee.osnews.repository.model.NewsWires


@OptIn(ExperimentalUnitApi::class)
@Composable
fun NewsWireCell(
    newsWire: NewsWire,
    modifier: Modifier = Modifier
) {
    val roundedShape = RoundedCornerShape(6.dp)
    BoxWithConstraints(
        modifier = modifier
            .background(
                color = Color.White,
                shape = roundedShape,
            )
            .shadow(
                elevation = 4.dp,
                shape = roundedShape
            )
            .clip(roundedShape),
        contentAlignment = Alignment.BottomCenter,
    ) {
        val multimedia = newsWire.multimedia?.maxBy { it.width }
        // https://coil-kt.github.io/coil/compose/
        SubcomposeAsyncImage(
            modifier = modifier.fillMaxWidth(),
            model = multimedia?.url?.let { crossFade(it) },
            loading = { CircularProgressIndicator() },
            contentDescription = multimedia?.copyright ?: "",
            contentScale = ContentScale.FillWidth,
        )

        Text(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .fillMaxWidth(),
            text = newsWire.title ?: "NO TITLE",
            fontSize = TextUnit(12f, TextUnitType.Sp),
            style = MaterialTheme.typography.h4.copy(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            ),
            textAlign = TextAlign.Center,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }

}

@Composable
private fun crossFade(imageUrl: String) = ImageRequest.Builder(LocalContext.current)
    .data(imageUrl)
    .crossfade(true)
    .build()


@Composable
fun NewsWireGrid(
    list: NewsWires,
    modifier: Modifier = Modifier,
) {
    val arrangement = Arrangement.spacedBy(16.dp)

    LazyVerticalGrid(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = arrangement,
        horizontalArrangement = arrangement,
        contentPadding = PaddingValues(12.dp),
        columns = GridCells.Adaptive(100.dp),
    ) {
        items(items = list) { NewsWireCell(it) }
    }
}


@Composable
@Preview
private fun previewCell() {
    NewsWireCell(newsWiresTestList.random())
}

@Composable
@Preview
private fun previewGrid() {
    NewsWireGrid(newsWiresTestList)
}

val newsWiresTestList = listOf(
    NewsWire(
        slugName = "00 KYSchools -FADER",
        section = "Home Page",
        subsection = "unknown",
        title = "Schools",
        abstract = "Schools",
        uri = "nyt://slideshow/da525698-07c7-5daa-8b87-72397a3148f6, url=https://static01.nyt.com/slideshow/2022/08/26/homepage/schools.html",
        byline = "",
        thumbnailStandard = "https://static01.nyt.com/images/2022/08/28/homepage/00KYSchools-FADER-slide-JPX1/00KYSchools-FADER-slide-JPX1-thumbStandard.jpg",
        itemType = "Slideshow",
        source = "New York Times",
        updatedDate = "2022 - 08 - 26 T13 :43:50 - 04:00",
        createdDate = "2022-08-26T13:37:37-04:00",
        publishedDate = "2022-08-26T13:37:37-04:00",
        firstPublishedDate = "2022-08-26T13:37:37-04:00",
        materialTypeFacet = "Slideshow",
        multimedia = listOf(
            Multimedia(
                url = "https://static01.nyt.com/images/2022/08/28/homepage/00KYSchools-FADER-slide-JPX1/00KYSchools-FADER-slide-JPX1-thumbStandard.jpg",
                format = "Standard Thumbnail",
                height = 75,
                width = 75,
                type = "image",
                subtype = "photo",
                caption = "",
                copyright = "Jared Hamilton for The New York Times",
            ),
            Multimedia(
                url = "https://static01.nyt.com/images/2022/08/28/homepage/00KYSchools-FADER-slide-JPX1/00KYSchools-FADER-slide-JPX1-mediumThreeByTwo210.jpg",
                format = "mediumThreeByTwo210",
                height = 140,
                width = 210,
                type = "image",
                subtype = "photo",
                caption = "",
                copyright = "Jared Hamilton for The New York Times",
            ),
        )
    ),


    NewsWire(
        slugName = "RANDOM",
        section = "Schools",
        subsection = "",
        title = "A very long long long long long long title",
        abstract = "Schools",
        uri = "nyt://slideshow/da525698-07c7-5daa-8b87-72397a3148f6, url=https://static01.nyt.com/slideshow/2022/08/26/homepage/schools.html",
        byline = "",
        thumbnailStandard = "https://static01.nyt.com/images/2022/08/28/homepage/00KYSchools-FADER-slide-JPX1/00KYSchools-FADER-slide-JPX1-thumbStandard.jpg",
        itemType = "Slideshow",
        source = "New York Times",
        updatedDate = "2022 - 08 - 26 T13 :43:50 - 04:00",
        createdDate = "2022-08-26T13:37:37-04:00",
        publishedDate = "2022-08-26T13:37:37-04:00",
        firstPublishedDate = "2022-08-26T13:37:37-04:00",
        materialTypeFacet = "Slideshow",
        multimedia = listOf(
            Multimedia(
                url = "https://static01.nyt.com/images/2022/08/28/homepage/00KYSchools-FADER-slide-JPX1/00KYSchools-FADER-slide-JPX1-thumbStandard.jpg",
                format = "Standard Thumbnail",
                height = 75,
                width = 75,
                type = "image",
                subtype = "photo",
                caption = "",
                copyright = "Jared Hamilton for The New York Times",
            ),
        )
    ),
)