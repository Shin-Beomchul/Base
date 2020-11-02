package com.godbeom.baseapp.model

import com.godbeom.baseapp.persistence.entity.Matchs
import java.text.SimpleDateFormat
import java.util.*

class MatchMapper {

    fun transform(matchResponse: MatchResponse): Matchs {
        return with(matchResponse) {
            Matchs(
                cnt = cnt,
                page = page,
                matchs = results.map {
                    Matchs.Match(
                        0,
                     offer_no = it.offer_no,
                     hosp_id= it.hosp_id,
                     sangsi_yn= it.sangsi_yn,
                     mozip_end_day = it.mozip_end_day,
                     mozip_end_dt = it.mozip_end_dt,
                     hospImage= Image(it.hosp_file_no?:""),
                     hosp_nm = it.hosp_nm,
                     adpt_titl= it.adpt_titl,
                     jobx_flg2_nm = it.jobx_flg2_nm,
                     hosp_region = it.hosp_region,
                     interest_yn = it.interest_yn,
                     tel_no = it.tel_no
                    )
                }
            )
        }
    }
}