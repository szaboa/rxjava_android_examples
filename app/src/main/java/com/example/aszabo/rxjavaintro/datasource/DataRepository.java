package com.example.aszabo.rxjavaintro.datasource;

import com.example.aszabo.rxjavaintro.api.GithubService;
import com.example.aszabo.rxjavaintro.api.RetrofitClient;
import com.example.aszabo.rxjavaintro.model.GithubRepository;
import com.example.aszabo.rxjavaintro.model.GithubUser;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by aszabo on 5/28/17.
 */

public class DataRepository {
    private static DataRepository instance;

    private GithubService service;

    private DataRepository() {
        service = RetrofitClient.getInstance().getGithubService();
    }

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }

        return instance;
    }

    public Observable<GithubUser> getUser(String username) {
        return service.getUser(username);
    }

    public Observable<List<GithubRepository>> getRepositories(String username) {
        return service.getUser(username)
                .map(user -> user.getName())
                .flatMap(name -> service.getRepositories(name));
    }
}
