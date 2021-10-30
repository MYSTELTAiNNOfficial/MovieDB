package com.spr.moviedb.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputLayout;
import com.spr.moviedb.R;
import com.spr.moviedb.adapter.PopularAdapter;
import com.spr.moviedb.adapter.SearchAdapter;
import com.spr.moviedb.model.Popular;
import com.spr.moviedb.model.Search;
import com.spr.moviedb.viewmodel.MovieVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    private Button button_searchfrag;
    private TextInputLayout ti_searchfrag;
    private RecyclerView rv_searchfrag;
    private MovieVM vm;
    private LinearLayoutManager llm;
    private SearchAdapter adapter;
    private String query = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        vm = new ViewModelProvider(getActivity()).get(MovieVM.class);
        llm = new LinearLayoutManager(getActivity());
        rv_searchfrag = view.findViewById(R.id.rv_searchfrag);
        button_searchfrag = view.findViewById(R.id.button_searchfrag);
        ti_searchfrag = view.findViewById(R.id.ti_searchfrag);
        query = ti_searchfrag.getEditText().getText().toString().trim();
        button_searchfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ti_searchfrag.getEditText().getText().toString().trim() == null){
                    ti_searchfrag.setError("Can't be null");
                }else{
                    query = ti_searchfrag.getEditText().getText().toString().trim();
                    ti_searchfrag.setError(null);
                    vm.getSearch(query);
                    vm.getResultGetSearch().observe(getActivity(), showSearch);
                }
            }
        });
        return view;
    }
    private Observer<Search> showSearch = new Observer<Search>() {
        @Override
        public void onChanged(Search search) {
            rv_searchfrag.setLayoutManager(llm);
            adapter = new SearchAdapter(getActivity());
            adapter.setListSc(search.getResults());
            rv_searchfrag.setAdapter(adapter);
        }
    };
}