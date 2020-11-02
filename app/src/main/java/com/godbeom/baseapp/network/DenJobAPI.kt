package com.godbeom.baseapp.network

import com.godbeom.baseapp.model.MatchResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DenJobAPI {


    @FormUrlEncoded
    @POST("offer.do?cmd=reqGetSetOfferList")
    fun getMatchOfferList(@Field("cur_page") cur_page:String,
                          @Field("user_id") user_id:String,
                          @Field("mem_basic_gbn_cd") mem_basic_gbn_cd:String,
                          @Field("reg_id") reg_id:String,
                          @Field("app_ver") app_ver:String
                        ): Single<MatchResponse>
}