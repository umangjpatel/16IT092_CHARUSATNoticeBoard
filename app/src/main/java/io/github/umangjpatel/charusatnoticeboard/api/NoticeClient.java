package io.github.umangjpatel.charusatnoticeboard.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeClient {

    private static final String BASE_URL = "http://172.16.11.70:8010/";

    private static Retrofit getInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NoticeService getNoticeService() {
        return getInstance().create(NoticeService.class);
    }

}
