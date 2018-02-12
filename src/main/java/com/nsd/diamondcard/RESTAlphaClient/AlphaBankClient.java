package com.nsd.diamondcard.RESTAlphaClient;


import com.nsd.diamondcard.RESTAlphaClient.RESTAlphaClienUtils.Utils;
import com.nsd.diamondcard.RESTAlphaClient.ResponceModels.AlphaBankPartner;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


import static com.nsd.diamondcard.RESTAlphaClient.AlphaConst.DEBUG_PARTNERS_LOGIN;
import static com.nsd.diamondcard.RESTAlphaClient.AlphaConst.DEBUG_USER1_PASSWORD;
import static com.nsd.diamondcard.RESTAlphaClient.AlphaConst.TOKEN_ENDPOINT_URL;

@Service
public class AlphaBankClient {

    private AlphaTokenService tokenService = null;

    private AlphaBankPartner currentPartner = null;


    public AlphaBankClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOKEN_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        tokenService = retrofit.create(AlphaTokenService.class);



        Call<AlphaBankPartner> req =  tokenService.getPartnerTokenWithRemote(Utils.generateHeaderValueParntesTokenString(DEBUG_PARTNERS_LOGIN,DEBUG_USER1_PASSWORD));
        try {
            Response response = req.execute();
            this.currentPartner = (AlphaBankPartner) response.body();
            System.out.println(currentPartner);
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        }
    }






}
