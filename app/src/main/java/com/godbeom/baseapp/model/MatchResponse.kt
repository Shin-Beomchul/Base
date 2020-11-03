package com.godbeom.baseapp.model

data class MatchResponse (
    var result_msg: String? = null,
    val result_cd: String? = null,
    val cnt: Int = 0, //total
    val page:Int = 0,
    val list: List<Match>
){
    data class Match(
         var offer_no: String? = null,
        val hosp_id: String? = null,
        val sangsi_yn: String?= null,
        val mozip_end_day: String? = null,
        val mozip_end_dt: String? = null,
        val hosp_file_no: String? = null,
        val hosp_nm: String? = null,
        val adpt_titl: String? = null,
        val jobx_flg2_nm: String? =   null,
        val hosp_region: String? = null,
        val interest_yn: String? = null,
        val tel_no: String? =  null
    )

}