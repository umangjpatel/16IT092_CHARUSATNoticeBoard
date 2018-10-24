package io.github.umangjpatel.charusatnoticeboard.api;

import java.util.List;

import io.github.umangjpatel.charusatnoticeboard.models.Notice;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NoticeService {

    @GET("/api/notices")
    Call<List<Notice>> getNotices();

}
