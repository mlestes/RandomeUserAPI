package com.coolcats.randomuserapi.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coolcats.randomuserapi.databinding.ListItemLayoutBinding;
import com.coolcats.randomuserapi.mod.User;
import com.coolcats.randomuserapi.util.MyLog;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemLayoutBinding binding = ListItemLayoutBinding.inflate
                (LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {

        User user = userList.get(position);
        holder.binding.userNameText.setText(user.getLogin().getUsername());
        holder.binding.nameText.setText(user.getName().getFirst() + " " + user.getName().getLast());
        holder.binding.ageText.setText(user.getDob().getAge()+"");
        holder.binding.genderText.setText(user.getGender());
        MyLog.logDebug("Picture: " + user.getPicture().getLarge());

        Glide.with(holder.binding.getRoot())
                .applyDefaultRequestOptions(RequestOptions.centerCropTransform())
                .load(user.getPicture().getLarge())
                .into(holder.binding.avatarImage);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        private ListItemLayoutBinding binding;
        public UserViewHolder(@NonNull ListItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
