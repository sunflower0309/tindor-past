package edu.uci.tindor.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.uci.tindor.Adapters.ChatAdapter;
import edu.uci.tindor.DataModel.Chat;
import edu.uci.tindor.R;
import edu.uci.tindor.Utils.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment {

    ChatAdapter chatAdapter = new ChatAdapter(new ArrayList<>());

    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView matchesRV = getActivity().findViewById(R.id.matchesRV);
        matchesRV.setHasFixedSize(false);
        matchesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        matchesRV.setAdapter(chatAdapter);
    }

    public void fetchData() {
        String[] names = {"Adria", "Chloe", "Dana", "Lillian", "Norah", "Rosie"};
        for(int i = 0; i < 6; ++i) {
            chatAdapter.addElement(
                    new Chat("1", "1", "1", names[i],
                            Config.BASE_URL + "cat_photo/" + i + ".jpg")
            );
        }
    }
}
