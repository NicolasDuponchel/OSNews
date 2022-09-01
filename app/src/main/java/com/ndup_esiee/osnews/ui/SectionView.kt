package com.ndup_esiee.osnews.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ndup_esiee.osnews.repository.model.Section
import com.ndup_esiee.osnews.repository.model.Sections
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun SectionCell(
    section: Section,
    modifier: Modifier = Modifier,
) {
    val roundedShape = RoundedCornerShape(8.dp)
    Box(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = roundedShape
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = roundedShape
            )
            .background(
                color = Color.White,
                shape = roundedShape,
            )
            .clip(roundedShape),
    ) {
        Text(
            modifier = Modifier
                .background(
                    color = randomColor(
                        section = section.displayName,
                        alpha = (0.6 * 255).roundToInt()
                    ),
                )
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .wrapContentSize(),
            text = section.displayName,
            textAlign = TextAlign.Center,
        )
    }

}

@Composable
fun SectionCells(
    list: Sections,
    modifier: Modifier = Modifier,
    onSectionSelected: (section: Section) -> Unit = {},
) {
    LazyRow(
        modifier = modifier.padding(vertical = 12.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items = list) {
            SectionCell(
                section = it,
                modifier = Modifier.clickable { onSectionSelected(it) }
            )
        }
    }
}

private fun randomColor(section: String, alpha: Int = 255): Color {
    fun randomInt(seed: Long) = Random(seed.times(Random.nextInt())).nextInt(256)
    val seed = section.hashCode().toLong()
    return Color(randomInt(seed), randomInt(seed), randomInt(seed), alpha)
}


@Composable
@Preview
private fun preview() {
    Column {
        SectionCells(
            sectionsTestList,
        )
    }
}

val sectionsTestList = listOf(
    "Admin",
    "Arts",
    "Automobiles",
    "Books",
    "Briefing",
    "Business",
    "Climate",
    "Corrections",
    "Crosswords & Games",
    "Education",
    "En Español",
    "Fashion",
    "Food",
    "Guides",
    "Health",
    "Home & Garden",
    "Home Page",
    "Job Market",
    "Lens",
    "Magazine",
    "Movies",
    "Multimedia/Photos",
    "New York",
    "Obituaries",
    "Opinion",
    "Parenting",
    "Podcasts",
    "Reader Center",
    "Real Estate",
    "Science",
    "Smarter Living",
    "Sports",
    "Style",
    "Sunday Review",
    "T Brand",
    "T Magazine",
    "Technology",
    "The Learning Network",
    "The Upshot",
    "The Weekly",
    "Theater",
    "Times Insider",
    "Today’s Paper",
    "Travel",
    "U.S.",
    "Universal",
    "Video",
    "Well",
    "World",
    "Your Money",
).map { Section(it.lowercase(), it) }