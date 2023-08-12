package org.pixlmars.blasphemy

data class RandomAnime(
    val data: AnimeData
)

data class AnimeData(
    val title: String,
    val title_japanese: String,
    val aired: Aired,
    val images: Images,
    val genres: List<Genre>
)

data class Aired(
    val string: String,
    val prop: Prop
)

data class Images(
    val jpg: ImageInfo
)

data class ImageInfo(
    val image_url: String
)

data class Genre(
    val name: String
)
data class Prop(
    val from: From
)
data class From(
    val year: String
)
