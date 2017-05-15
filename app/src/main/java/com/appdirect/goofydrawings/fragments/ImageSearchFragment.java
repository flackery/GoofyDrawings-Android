package com.appdirect.goofydrawings.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdirect.goofydrawings.adapters.ImageItemAdapter;
import com.appdirect.goofydrawings.R;
import com.appdirect.goofydrawings.activities.MainActivity;
import com.appdirect.goofydrawings.api.GoogleSearchApi;
import com.appdirect.goofydrawings.api.ImageSearchResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by mehlert on 2017-05-12.
 */

public class ImageSearchFragment extends Fragment {

    private static final String ARG_QUERY = "query";

    private GridLayoutManager gridLayout;
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    ImageItemAdapter recyclerAdapter;
    private MainActivity mActivity;

    public static ImageSearchFragment newInstance(String query) {

        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);

        ImageSearchFragment fragment = new ImageSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        this.setHasOptionsMenu(true);

        ButterKnife.bind(this, view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        gridLayout = new GridLayoutManager(getContext(), 3);


        GoogleSearchApi.getInstance().searchImages(getArguments().getString(ARG_QUERY)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ImageSearchResult>() {
            @Override
            public void accept(ImageSearchResult imageSearchResult) throws Exception {
                updateList(imageSearchResult.items);
            }
        });

        return view;
    }

    private void updateList(final List<ImageSearchResult.Item> images) {

        recyclerAdapter = new ImageItemAdapter(getContext(), images, new ImageItemAdapter.OnItemClickListener() {
            @Override
            public void onClicked(int position) {
                mActivity.setReferenceImage(recyclerAdapter.getItem(position).link);
            }
        });

        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @OnClick(R.id.close)
    public void close(View view) {
        mActivity.getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

}
