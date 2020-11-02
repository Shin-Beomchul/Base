package com.godbeom.baseapp.persistence.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.godbeom.baseapp.model.Image
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Matchs(
    var result_msg: String? = null,
    val result_cd: String? = null,
    val cnt: Int = 0,
    val page:Int= 0,
    val matchs: List<Match>
): Parcelable {

    //img : Const.URL.DENJOB_IMG_URL + "&file_no=" + offer.getHosp_file_no()
    @Parcelize
    data class Match(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        var offer_no: String? = null,
        val hosp_id: String? = null,
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
    data class MatcRemotehKeys(
        @PrimaryKey val offer_no: Long,
        val prevKey: Int?,
        val nextKey: Int?
    ) : Parcelable
}