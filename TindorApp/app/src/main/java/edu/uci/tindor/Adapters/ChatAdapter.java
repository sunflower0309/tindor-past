package edu.uci.tindor.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.uci.tindor.ChatActivity;
import edu.uci.tindor.DataModel.Chat;
import edu.uci.tindor.R;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<Chat> chats;

    public void addElement(Chat chat) {
        chats.add(chat);
        notifyDataSetChanged();
    }

    class ChatsViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        ImageView image;
        TextView name;
        View view;

        public ChatsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            layout = itemView.findViewById(R.id.chatLayout);
            image = itemView.findViewById(R.id.chatPictureIV);
            name = itemView.findViewById(R.id.chatNameTV);
        }

        void bind(Chat chat) {
            name.setText(chat.name);
            if(image != null) {
                Glide.with(view)
                     .load(chat.imageUrl)
                     .into(image);
            }
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //, chat.chatId, chat.userId, chat.imageUrl, chat.otherUserId
                   Intent intent = new Intent(view.getContext(), ChatActivity.class);
                   intent.putExtra("name", name.getText());
                   intent.putExtra("imageUrl", chat.imageUrl);
                   view.getContext().startActivity(intent);
                }
            });
        }
    }

    public ChatAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ChatsViewHolder)holder).bind(chats.get(position));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
