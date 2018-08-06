package com.biryanify.parichay.biryanify;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class RecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<DailyOrder> mDailyOrders;
    String date;

    public RecyclerViewFragment() {
        mDailyOrders = new ArrayList<>();
        date = "";
    }

    public static RecyclerViewFragment newInstance(ArrayList<DailyOrder> dailyOrders, String date) {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("orders", dailyOrders);
        bundle.putString("date", date);
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
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        Bundle bundle = getArguments();
        mDailyOrders = bundle.getParcelableArrayList("orders");
        date = bundle.getString("date");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        OrderAdapter adapter = new OrderAdapter(mDailyOrders);
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
                                    R.id.fragment_container2,
                                    ViewOrderFragment.newInstance(mDailyOrders.get(position), date),
                                    null
                                )
                            .addToBackStack(null)
                            .commit();
                    }
                }));

        return view;
    }

}
