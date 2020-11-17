package com.iet.taskplanner.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iet.taskplanner.LoginActivity;
import com.iet.taskplanner.R;

public class SlideshowFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences sharedPref =
                getActivity().getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE );
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.clear();
        edit.commit();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        return root;
    }
}