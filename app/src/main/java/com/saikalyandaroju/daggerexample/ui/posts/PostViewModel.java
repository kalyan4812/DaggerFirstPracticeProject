package com.saikalyandaroju.daggerexample.ui.posts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.saikalyandaroju.daggerexample.SessionManager;
import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.Utils.SingleLiveEvent;
import com.saikalyandaroju.daggerexample.data.NetworkSource.MainApiSource;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.Post;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";
    private final SessionManager sessionManager;
    private final MainApiSource mainApiSource;

    private MediatorLiveData<ApiResponse<List<Post>>> posts;


    @Inject
    public PostViewModel(SessionManager sessionManager,MainApiSource mainApiSource) {
        this.sessionManager=sessionManager;
        this.mainApiSource=mainApiSource;
    }

    public LiveData<ApiResponse<List<Post>>> observePots() {
        if(posts==null) {
            posts=new MediatorLiveData<>();
            sessionManager.loadingStatus.setValue(true);
           final LiveData<ApiResponse<List<Post>>> liveData= LiveDataReactiveStreams.fromPublisher(
                    mainApiSource.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId()).onErrorReturn(new Function<Throwable, List<Post>>() {
                        @Override
                        public List<Post> apply(@NonNull Throwable throwable) throws Exception {
                            Post post=new Post();
                            post.setUserId(-1);
                            ArrayList<Post> posts=new ArrayList<>();
                            posts.add(post);
                            return posts;
                        }
                    }).map(new Function<List<Post>, ApiResponse<List<Post>>>() {
                        @Override
                        public ApiResponse<List<Post>> apply(@NonNull List<Post> posts) throws Exception {
                            if(posts.size()>0){
                                if(posts.get(0).getUserId()==-1){
                                  return new ApiResponse(0,"failed",null);
                                }
                            }
                            return new ApiResponse<>(1,posts,null);
                        }
                    }).subscribeOn(Schedulers.io())


            );
           posts.addSource(liveData, new Observer<ApiResponse<List<Post>>>() {
               @Override
               public void onChanged(ApiResponse<List<Post>> listApiResponse) {
                   posts.setValue(listApiResponse);
                   posts.removeSource(liveData);
                   sessionManager.loadingStatus.setValue(false);
               }
           });

        }
        return posts;
    }
    public SessionManager getSessionManager(){
        return sessionManager;
    }

}
