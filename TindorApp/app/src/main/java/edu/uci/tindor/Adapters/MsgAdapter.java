package edu.uci.tindor.Adapters;

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

import edu.uci.tindor.DataModel.Chat;
import edu.uci.tindor.DataModel.Message;
import edu.uci.tindor.R;

public class MsgAdapter extends RecyclerView.Adapter{

    ArrayList<Message> messages;
    String userId;
    final static int MESSAGE_CURRENT_USER = 1;
    final static int MESSAGE_OTHER_USER = 2;

    public void addMessage(Message message) {
        messages.add(message);
        notifyDataSetChanged();
    }

    class MsgViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        ImageView image;
        TextView name;
        View view;

        public MsgViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            layout = itemView.findViewById(R.id.chatLayout);
            image = itemView.findViewById(R.id.chatPictureIV);
            name = itemView.findViewById(R.id.chatNameTV);
        }

        void bind(Message msg) {
            ((TextView)view.findViewById(R.id.messageTV)).setText(msg.message);
        }
    }

    public MsgAdapter(ArrayList<Message> msgs, String userId) {
        this.messages = msgs;
        this.userId = userId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MESSAGE_CURRENT_USER) {
            return new MsgViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_self_msg, parent, false)
            );
        } else {
            return new MsgViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_msg, parent, false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MsgViewHolder)holder).bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).sentBy.equals(userId)) {
            return MESSAGE_CURRENT_USER;
        } else {
            return MESSAGE_OTHER_USER;
        }
    }
}
