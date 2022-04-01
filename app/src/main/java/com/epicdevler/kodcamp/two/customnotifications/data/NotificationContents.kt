package com.epicdevler.kodcamp.two.customnotifications.data

import com.epicdevler.kodcamp.two.customnotifications.R
import com.epicdevler.kodcamp.two.customnotifications.data.models.NotificationContentsModel

object NotificationContents {

    fun getRemoteData(): List<NotificationContentsModel> = listOf(
        NotificationContentsModel(
            title = "Adam Walfelt",
            content = R.string.adam_walfelt,
            infoGraphic = R.drawable.adam_wolfelt
        ),
        NotificationContentsModel(
            title = "Dolly Parton",
            content = R.string.dolly_parton,
            infoGraphic = R.drawable.dolly_parton
        ),
        NotificationContentsModel(
            title = "Thomas Keller",
            content = R.string.thomas_keller,
            infoGraphic = R.drawable.thomas_keller
        ),
        NotificationContentsModel(
            title = "Winona Laduke",
            content = R.string.winona_laduke,
            infoGraphic = R.drawable.winona_laduke
        ),
        NotificationContentsModel(
            title = "Miss Piggy",
            content = R.string.miss_piggy,
            infoGraphic = R.drawable.miss_piggy
        ),
    )

}