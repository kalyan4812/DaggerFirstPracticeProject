package com.saikalyandaroju.daggerexample.ui.posts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saikalyandaroju.daggerexample.R;
import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.Utils.VerticalSpaceItemDecoration;
import com.saikalyandaroju.daggerexample.Utils.ViewModelProviderFactory;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.Post;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragement extends DaggerFragment {
    private static final String TAG = "PostsFragement";
    private PostViewModel postViewModel;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       recyclerView=view.findViewById(R.id.recycler_view);
       postViewModel= ViewModelProviders.of(this,viewModelProviderFactory).get(PostViewModel.class);
       progressDialog=new ProgressDialog(getActivity());
       susbscribeObservers();
       postViewModel.getSessionManager().loadingStatus.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
           @Override
           public void onChanged(Boolean aBoolean) {
               if(aBoolean){
                   progressDialog.show();
               }
               else {
                   progressDialog.dismiss();
               }
           }
       });
       susbscribeObservers();
       initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration verticalSpaceItemDecoration=new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(verticalSpaceItemDecoration);
        recyclerView.setAdapter(postsRecyclerAdapter);
    }

    private void susbscribeObservers() {
        postViewModel.observePots().removeObservers(getViewLifecycleOwner());
        postViewModel.observePots().observe(getViewLifecycleOwner(), new Observer<ApiResponse<List<Post>>>() {
            @Override
            public void onChanged(ApiResponse<List<Post>> listApiResponse) {
                 if(listApiResponse!=null){
                     if(listApiResponse.status==1){
                      postsRecyclerAdapter.setPosts(listApiResponse.data);
                     }
                     else {
                         Log.i(TAG,"Failed to load........");
                     }
                 }
            }
        });

    }
}
