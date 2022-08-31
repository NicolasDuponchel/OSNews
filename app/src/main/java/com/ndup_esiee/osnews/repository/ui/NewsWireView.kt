package com.ndup_esiee.osnews.repository.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndup_esiee.osnews.repository.model.Multimedia
import com.ndup_esiee.osnews.repository.model.NewsWire
import com.ndup_esiee.osnews.repository.model.NewsWires


@Composable
fun NewsWireCell(newsWire: NewsWire) {
    val roundedShape = RoundedCornerShape(8.dp)
    Text(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = roundedShape,
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = roundedShape
            )
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .wrapContentSize(),
        text = newsWire.title ?: "NO TITLE",
        textAlign = TextAlign.Center,
    )
}


@Composable
fun NewsWireGrid(
    list: NewsWires,
    modifier: Modifier = Modifier,
) {
    val arrangement = Arrangement.spacedBy(16.dp)

//    LazyHorizontalGrid(
//        modifier = modifier
//            .fillMaxWidth()
//            .heightIn(20.dp, 150.dp)
////            .height(100.dp)
//            .padding(10.dp),
//        verticalArrangement = arrangement,
//        horizontalArrangement = arrangement,
//        contentPadding = PaddingValues(12.dp),
////        rows = GridCells.Adaptive(16.dp),
//        rows = GridCells.Fixed(3),
//    ) {
//        items(items = list) { SectionCell(it) }
//    }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = arrangement,
        horizontalArrangement = arrangement,
        contentPadding = PaddingValues(12.dp),
        columns = GridCells.Adaptive(60.dp),
    ) {
        items(items = list) {
            NewsWireCell(it)
        }
    }
}


@Composable
@Preview
private fun preview() {
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