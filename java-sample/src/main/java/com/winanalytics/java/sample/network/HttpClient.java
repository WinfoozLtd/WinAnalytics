package com.winanalytics.java.sample.network;

import com.winanalytics.java.sample.models.Post;
import com.winfooz.AnalyticsCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Project: WinAnalytics2
 * Created: November 23, 2018
 *
 * @author Mohamed Hamdan
 */
public interface HttpClient {

    @AnalyticsCall
    @GET("posts")
    Call<List<Post>> getPosts();
}
