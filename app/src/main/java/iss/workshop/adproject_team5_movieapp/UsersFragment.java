package iss.workshop.adproject_team5_movieapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;


import java.util.List;

import iss.workshop.adproject_team5_movieapp.Adapter.OnFollowClickListener;
import iss.workshop.adproject_team5_movieapp.Adapter.UserAdapter;
import iss.workshop.adproject_team5_movieapp.Model.Social;
import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.Model.WatchLater;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitService;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment implements OnFollowClickListener {

    private RecyclerView recyclerView;
    User user;
    User user1;
    private String name;
    UserAdapter userAdapter;
    private SearchView searchView;

    //private List<User> userModelArrayList;
    public UsersFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Type Here to search");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                name =searchView.getQuery().toString();
//                loadEmployee(name);
//                System.out.println("search query submit");
//                System.out.println(name);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return false;
//            }
//        });
//
//    }

    private void loadEmployee(String name)
    {
        RetrofitService retrofitService = new RetrofitService();
        MovieApi movieApi= retrofitService.getRetrofit().create(MovieApi.class);

        movieApi.getAllUserByName(name)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        //list users from Users
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        //Toast.makeText(UserListActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        user1 = (User) bundle.getSerializable("user");

        //userModelArrayList = new ArrayList<>();
        return inflater.inflate(R.layout.activity_user_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.userList);
//        UserAdapter userAdapter= new UserAdapter(this);
//        recyclerView.setAdapter(userAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        //loadEmployee(name);
        System.out.println(name);
        searchView = view.findViewById(R.id.search_view1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                name =searchView.getQuery().toString();
                loadEmployee(name);
                System.out.println("search query submit");
                System.out.println(name);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }


    @Override
    public void onFollowClick(View v,int position){
        User u=userAdapter.getSelectedUser(position);
        Log.v("please", u.getName());
        Button followBtn=v.findViewById(R.id.followBtn);
        switch (v.getId()) {
            case R.id.viewBtn:
                Log.v("please", "view");
                Intent intent = new Intent(getActivity(), UserHistory.class);
                intent.putExtra("user",u);
                intent.putExtra("user1", user1);
                startActivity(intent);
                break;
            case R.id.followBtn:
                Log.v("please", "follow");
                iss.workshop.adproject_team5_movieapp.Request.RetrofitService retrofitService = new iss.workshop.adproject_team5_movieapp.Request.RetrofitService();
                MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
                Social social= new Social(user1.getUserId(),u.getUserId());
                if(followBtn.isSelected()){
                    followBtn.setSelected(false);
                    followBtn.setText("Follow");
                    movieApi.deleteSocial(user1.getUserId(),u.getUserId())
                            .enqueue(new Callback<Social>() {
                                @Override
                                public void onResponse(Call<Social> call, Response<Social> response) {
                                    Toast.makeText(getContext(), "Delete from  Social", Toast.LENGTH_SHORT)
                                            .show();
                                }

                                @Override
                                public void onFailure(Call<Social> call, Throwable t) {

                                }
                            });
                }
                else{
                    followBtn.setSelected(true);
                    followBtn.setText("Unfollow");
                    movieApi.save(social)
                            .enqueue(new Callback<Social>() {
                                @Override
                                public void onResponse(Call<Social> call, Response<Social> response) {

                                    Toast.makeText(getContext(), "Added to Social", Toast.LENGTH_SHORT)
                                            .show();
                                }
                                @Override
                                public void onFailure(Call<Social> call, Throwable t) {
                                    Toast.makeText(getContext(), "Failed to add to Social", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });}
                break;
        }
//        iss.workshop.adproject_team5_movieapp.Request.RetrofitService retrofitService = new iss.workshop.adproject_team5_movieapp.Request.RetrofitService();
//        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);
//        Social social= new Social(user.getUserId(),u.getUserId());
//         if(followBtn.isSelected()){
//             followBtn.setSelected(false);
//            followBtn.setText("Follow");
//            movieApi.deleteSocial(user.getUserId(),u.getUserId())
//                    .enqueue(new Callback<Social>() {
//                        @Override
//                        public void onResponse(Call<Social> call, Response<Social> response) {
//                            Toast.makeText(getContext(), "Delete from  Social", Toast.LENGTH_SHORT)
//                                    .show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Social> call, Throwable t) {
//
//                        }
//                    });
//        }
//        else{
//            followBtn.setSelected(true);
//            followBtn.setText("Unfollow");
//        movieApi.save(social)
//                .enqueue(new Callback<Social>() {
//                    @Override
//                    public void onResponse(Call<Social> call, Response<Social> response) {
//
//                        Toast.makeText(getContext(), "Added to Social", Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                    @Override
//                    public void onFailure(Call<Social> call, Throwable t) {
//                        Toast.makeText(getContext(), "Failed to add to Social", Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                });}


    }

    private void populateListView(List<User> userList) {
        userAdapter = new UserAdapter(userList,this,user1.getUserId());
        recyclerView.setAdapter(userAdapter);
    }
    @Override
    public void onResume() {

        super.onResume();
        loadEmployee(name);
    }


}