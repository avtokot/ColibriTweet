package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avtokot.colibritweet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pojo.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> userList = new ArrayList<>();

    private OnUserClickListener onUserClickListener;

    public UsersAdapter(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setItems(Collection<User> users) {
        userList.addAll(users);
        notifyDataSetChanged();
    }

    public void clearItems() {
        userList.clear();
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImageView;
        private TextView nameTextView;
        private TextView nickTextView;

        // Инициализация View - компонентов происходит в конструкторе
        public UserViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.profile_image_view);
            nameTextView = itemView.findViewById(R.id.user_name_text_view);
            nickTextView = itemView.findViewById(R.id.user_nick_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = userList.get(getLayoutPosition());
                    onUserClickListener.onUserClick(user);
                }
            });
        }

        public void bind(User user) {
            nameTextView.setText(user.getName());
            nickTextView.setText(user.getNick());

            Picasso.with(itemView.getContext())
                    .load(user.getImageUrl())
                    .into(userImageView);
        }
    }

    public interface OnUserClickListener {
        void onUserClick(User user);
    }
}
