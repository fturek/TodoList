package com.fturek.todolist.data.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class TodoItem(
    var title: String? = null,
    var description: String? = null,
    var createdAt: Timestamp? = null,
    var iconUrl: String? = null
) : Parcelable
