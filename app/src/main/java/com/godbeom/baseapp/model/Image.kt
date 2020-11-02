package com.godbeom.baseapp.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val hostFileNo: String): Parcelable {
    companion object {
        private const val PATH = "https://job.denall.com/file.do?cmd=download"
    }

    @IgnoredOnParcel
    val small: Uri = Uri.parse("$PATH/&file_no=$hostFileNo")

    @IgnoredOnParcel
    val medium: Uri = Uri.parse("$PATH/&file_no=$hostFileNo")

    @IgnoredOnParcel
    val large: Uri = Uri.parse("$PATH/&file_no=$hostFileNo")

    @IgnoredOnParcel
    val original: Uri = Uri.parse("$PATH/&file_no=$hostFileNo")
}