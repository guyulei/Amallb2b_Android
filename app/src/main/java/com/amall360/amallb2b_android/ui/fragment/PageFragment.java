package com.amall360.amallb2b_android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.amall360.amallb2b_android.R;

public class PageFragment extends Fragment {
    @LayoutRes int LayoutRes;

    public static PageFragment newInstance(@LayoutRes int layoutRes) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("layoutRes", layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.viewStub);
        viewStub.setLayoutResource(LayoutRes);
        viewStub.inflate();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            LayoutRes = args.getInt("layoutRes");
        }
    }
}
