package com.mediasoftindonesia.serviceapps.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mediasoftindonesia.serviceapps.R;
import com.mediasoftindonesia.serviceapps.activity.LoginActivity;
import com.mediasoftindonesia.serviceapps.adapter.HistoryAdapter;
import com.mediasoftindonesia.serviceapps.model.History;
import com.mediasoftindonesia.serviceapps.model.PostResponseHistory;
import com.mediasoftindonesia.serviceapps.model.PostResponseStore;
import com.mediasoftindonesia.serviceapps.network.ApiService;
import com.mediasoftindonesia.serviceapps.network.RetrofitBuilder;
import com.mediasoftindonesia.serviceapps.token.TokenManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "HistoryFragment";
    private ImageView ivLogout;
    private RelativeLayout rlNeedLogin;
    private Button btnLogin;

    private HistoryAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private ApiService service;
    private TokenManager tokenManager;
    private Call<PostResponseHistory> call;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_history_fragment, container, false);

        ivLogout = view.findViewById(R.id.image_view_logout);
        rlNeedLogin = view.findViewById(R.id.relative_layout_need_login);
        btnLogin = view.findViewById(R.id.loginButtonHistory);
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogout();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE));

        if (tokenManager.getToken() == null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
        if (tokenManager.getToken().getToken() != null) {
            getDataHistory();
            ivLogout.setVisibility(View.VISIBLE);
            rlNeedLogin.setVisibility(View.GONE);
        }else {
            ivLogout.setVisibility(View.GONE);
            rlNeedLogin.setVisibility(View.VISIBLE);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            });
        }

        return view;
    }

    private void getDataHistory() {
        call = service.postsDataHistory();
        call.enqueue(new Callback<PostResponseHistory>() {
            @Override
            public void onResponse(Call<PostResponseHistory> call, Response<PostResponseHistory> response) {
                Log.w(TAG, "onResponse: " + response);

                if (response.isSuccessful()) {
                    List<History> historyList = response.body().getHistory();
                    adapter = new HistoryAdapter(historyList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<PostResponseHistory> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void doLogout() {
        TokenManager tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE));
        tokenManager.deleteToken();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

}
