package edu.uci.tindor.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uci.tindor.Adapters.UserCardAdapter;
import edu.uci.tindor.R;
import edu.uci.tindor.DataModel.User;
import edu.uci.tindor.StartupActivity;
import edu.uci.tindor.Utils.Config;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeFragment extends Fragment {

    private String userId;
    private ArrayAdapter<User> cardsAdapter;
    private ArrayList<User> rowItems = new ArrayList<>();

    public SwipeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardsAdapter = new UserCardAdapter(getContext(), R.layout.item_card, rowItems);
        SwipeFlingAdapterView frame = getActivity().findViewById(R.id.frame);
        frame.setAdapter(cardsAdapter);
        frame.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                rowItems.remove(0);
                cardsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });

        ImageButton like = getActivity().findViewById(R.id.likeButton);
        ImageButton dislike = getActivity().findViewById(R.id.dislikeButton);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rowItems.isEmpty()) {
                    Toast.makeText(getActivity(), "Dislike!", Toast.LENGTH_SHORT).show();
                    frame.getTopCardListener().selectRight();
                }
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rowItems.isEmpty()) {
                    Toast.makeText(getActivity(), "Like!", Toast.LENGTH_SHORT).show();
                    frame.getTopCardListener().selectLeft();
                }
            }
        });

        if (rowItems.isEmpty())
            populateItems();
    }

    String url = Config.BASE_URL + "all_users";

    void populateItems() {
//        LinearLayout noUsersLayout = getActivity().findViewById(R.id.noUsersLayout);
//        LinearLayout progressLayout = getActivity().findViewById(R.id.progressLayout);
//        noUsersLayout.setVisibility(View.GONE);
//        progressLayout.setVisibility(View.VISIBLE);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    return;
                }

                final String responseData = response.body().string();

                JSONObject js;
                try {
                    js = new JSONObject(responseData);
                    JSONArray names = js.names();
                    for (int i = 0; i < js.names().length(); ++i) {
                        User u = new User();
                        String name = names.getString (i);
                        JSONObject user_js = js.getJSONObject(name);
                        u.name = name;
                        u.age = user_js.getString("age");
                        u.imageUrl = user_js.getString("photo_url");
                        rowItems.add(u);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cardsAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });

//        progressLayout.setVisibility(View.GONE);
    }

}
