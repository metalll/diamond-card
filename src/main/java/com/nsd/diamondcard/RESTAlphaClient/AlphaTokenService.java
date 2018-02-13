package com.nsd.diamondcard.RESTAlphaClient;


import com.nsd.diamondcard.RESTAlphaClient.ResponceModels.AlphaBankPartner;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import static com.nsd.diamondcard.RESTAlphaClient.AlphaConst.HEADER_AUTH_KEY;

public interface AlphaTokenService {

    @POST("token?grant_type=client_credentials&scope=read")
    Call<AlphaBankPartner> getPartnerTokenWithRemote(@Header(HEADER_AUTH_KEY) String key);

//    https://testjmb.alfabank.ru/uapidemo/api/oauth/token?grant_type=client_credentials&scope=read
}
