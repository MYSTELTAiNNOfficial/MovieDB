package com.spr.moviedb.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spr.moviedb.R;
import com.spr.moviedb.adapter.PopularAdapter;
import com.spr.moviedb.model.Popular;
import com.spr.moviedb.viewmodel.MovieVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PopularFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopularFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularFragment newInstance(String param1, String param2) {
        PopularFragment fragment = new PopularFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView rv_popfrag;
    private MovieVM vm;
    private LinearLayoutManager llm;
    private PopularAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        vm = new ViewModelProvider(getActivity()).get(MovieVM.class);
        rv_popfrag = view.findViewById(R.id.rv_popfrag);
        vm.getPopular();
        vm.getResultGetPopular().observe(getActivity(), showPopular);
        return view;

    }

    private Observer<Popular> showPopular = new Observer<Popular>() {
        @Override
        public void onChanged(Popular popular) {
            llm = new LinearLayoutManager(getActivity());
            rv_popfrag.setLayoutManager(llm);
            adapter = new PopularAdapter(getActivity());
            adapter.setListPop(popular.getResults());
            rv_popfrag.setAdapter(adapter);
        }
    };
}