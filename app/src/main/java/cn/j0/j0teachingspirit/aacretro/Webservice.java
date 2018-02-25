package cn.j0.j0teachingspirit.aacretro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aa on 2018/2/25.
 */

public interface Webservice {

    @GET("task/workbook/list.json")
    Call<BaseEntity<List<Book>>> getBooks(@Query("pageNum") int pageNum,
                                          @Query("pageSize") int pageSize,
                                          @Query("gradeId") int gradeId,
                                          @Query("type") String type,
                                          @Query("uuid") String uuid);
}
