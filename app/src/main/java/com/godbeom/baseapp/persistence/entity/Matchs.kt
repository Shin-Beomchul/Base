package com.godbeom.baseapp.persistence.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.godbeom.baseapp.model.Image
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
/*
* VM겸 Entity
* */
@Parcelize
data class Matchs(
    var result_msg: String? = null,
    val result_cd: String? = null,
    val cnt: Int = 0,
    val page:Int= 0,
    val matchs: List<Match>
): Parcelable {

    @IgnoredOnParcel
    val endOfPage = cnt <= page * 20 // FIXME

    @Parcelize
    @Entity(tableName = "matchs") // remoteMediator
    data class Match(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        var offer_no: String? = null,
        val hosp_id: String,
        val sangsi_yn: String?= null,
        val mozip_end_day: String? = null,
        val mozip_end_dt: String? = null,
        val hospImage: Image? = null,
        val hosp_nm: String? = null,
        val adpt_titl: String? = null,
        val jobx_flg2_nm: String? =   null,
        val hosp_region: String? = null,
        val interest_yn: String? = null,
        val tel_no: String? =  null
    ):Parcelable

    @Parcelize
    @Entity(tableName = "match_remote_keys")
    data class MatchRemotehKeys(
        @PrimaryKey val hosp_id: String,
        val prevKey: Int?,
        val nextKey: Int?
    ) : Parcelable
}