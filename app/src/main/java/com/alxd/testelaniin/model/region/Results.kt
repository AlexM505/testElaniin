package com.alxd.testelaniin.model.region

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
    val name: String,
    val url: String
) : Parcelable