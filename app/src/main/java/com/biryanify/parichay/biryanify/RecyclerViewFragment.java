package com.biryanify.parichay.biryanify;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class RecyclerViewFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private ArrayList<DailyOrder> dailyOrders;

    public RecyclerViewFragment() {
        dailyOrders = new ArrayList<>();
    }

    public static RecyclerViewFragment newInstance(ArrayList<DailyOrder> dailyOrders) {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("orders", dailyOrders);
        recyclerViewFragment.setArguments(bundle);
        return recyclerViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mRecyclerView = view.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        Bundle bundle = getArguments();
        dailyOrders = bundle.getParcelableArrayList("orders");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        OrderAdapter adapter = new OrderAdapter(dailyOrders);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new MyTouchListener(getContext(), mRecyclerView,
                new MyTouchListener.OnTouchActionListener() {
                    @Override
                    public void onLeftSwipe(View view, int position) {

                    }

                    @Override
                    public void onRightSwipe(View view, int position) {

                    }

                    @Override
                    public void onClick(View view, int position) {
                        MainActivity.fragmentManager.beginTransaction()
                            .replace
                                (
                                    R.id.fragment_container_main,
                                    ViewOrderFragment.newInstance(dailyOrders.get(position)),
                                    null
                                )
                                .addToBackStack(null)
                                .commit();
                    }
                }));

        return view;
    }

}
