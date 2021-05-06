package com.coolcats.randomuserapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.coolcats.randomuserapi.R;
import com.coolcats.randomuserapi.databinding.ActivityMainBinding;
import com.coolcats.randomuserapi.mod.User;
import com.coolcats.randomuserapi.presenter.Contract;
import com.coolcats.randomuserapi.presenter.UserPresenter;
import com.coolcats.randomuserapi.util.MyLog;
import com.coolcats.randomuserapi.util.Status;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private ActivityMainBinding binding;
    private UserAdapter adapter = new UserAdapter(new ArrayList<>());
    private Contract.Presenter presenter = new UserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setAdapter(adapter);
        binding.sendButton.setOnClickListener(v -> {
            String value = binding.searchText.getText().toString().trim();
            if(value.isEmpty())
                Toast.makeText(this, "Field must not be empty", Toast.LENGTH_SHORT).show();
            else {
                int number = Integer.parseInt(value);
                presenter.getUsers(number);
            }
        });
    }

    @Override
    public void showUsers(List<User> userList) {
        adapter.setUserList(userList);
    }

    @Override
    public void showError(String message) {
        MyLog.logError(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setStatus(Status status) {

        switch(status){

            case LOADING:
                binding.progressBar.setVisibility(View.VISIBLE);
                break;
            case COMPLETED:
                binding.progressBar.setVisibility(GONE);
                break;
            case ERROR:
                binding.progressBar.setVisibility(GONE);
                Toast.makeText(this, "An error occurred...", Toast.LENGTH_SHORT).show();

        }

    }
}