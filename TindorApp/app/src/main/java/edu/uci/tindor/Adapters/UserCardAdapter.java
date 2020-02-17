package edu.uci.tindor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.uci.tindor.R;
import edu.uci.tindor.DataModel.User;
import edu.uci.tindor.UserProfileActivity;

public class UserCardAdapter extends ArrayAdapter {

    public UserCardAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = (User)getItem(position);
        Context ctx = getContext();
        View finalView = LayoutInflater.from(ctx).inflate(R.layout.item_card, parent, false);
        TextView name = finalView.findViewById(R.id.nameTV);
        ImageView image = finalView.findViewById(R.id.photoIV);
        LinearLayout userInfo = finalView.findViewById(R.id.userInfoLayout);
        name.setText(user.name + ", " + user.age);
        Glide.with(getContext())
                .load(user.imageUrl)
                //.load(new File(user.imageUrl))
                .into(image);

        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finalView.getContext().startActivity(
                        new Intent(finalView.getContext(), UserProfileActivity.class)
                );
            }
        });
        return finalView;
    }
}
