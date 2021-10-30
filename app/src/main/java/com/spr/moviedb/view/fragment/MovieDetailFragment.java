package com.spr.moviedb.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.spr.moviedb.R;
import com.spr.moviedb.helper.Const;
import com.spr.moviedb.model.Movies;
import com.spr.moviedb.viewmodel.MovieVM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance(String param1, String param2) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = ProgressDialog.show(getActivity(), "", "Loading. Please wait...", true);
        dialog.show();
    }

    private TextView txt_show_md_frag, txt_rate_md_frag, txt_desc_md_frag, txt_rd_md_frag,
            txt_status_md_frag, txt_tagline_md_frag, txt_popular_md_frag;
    private ImageView cover_md_frag, backdrop_md_frag;
    private LinearLayout llh_image_md_frag;
    private String movie_id = "";
    private ChipGroup chipGroup;
    private MovieVM vm;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        txt_show_md_frag = view.findViewById(R.id.txt_show_md_frag);
        txt_rate_md_frag = view.findViewById(R.id.txt_rate_md_frag);
        txt_desc_md_frag = view.findViewById(R.id.txt_desc_md_frag);
        txt_rd_md_frag = view.findViewById(R.id.txt_rd_md_frag);
        txt_status_md_frag = view.findViewById(R.id.txt_status_md_frag);
        cover_md_frag = view.findViewById(R.id.cover_md_frag);
        backdrop_md_frag = view.findViewById(R.id.backdrop_md_frag);
        chipGroup = view.findViewById(R.id.cg_genre_md_frag);
        llh_image_md_frag = view.findViewById(R.id.llh_image_md_frag);
        txt_tagline_md_frag = view.findViewById(R.id.txt_tagline_md_frag);
        txt_popular_md_frag = view.findViewById(R.id.txt_popular_md_frag);

        String movie_id = getArguments().getString("movie_id").toString();
        vm = new ViewModelProvider(getActivity()).get(MovieVM.class);
        vm.getMovieById(movie_id);
        vm.getResultGetMovieById().observe(getActivity(), showResultMovie);
        return view;
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String title = movies.getTitle();
            String desc = movies.getOverview();
            String rate = String.valueOf(movies.getVote_average());
            String vote = Integer.toString(movies.getVote_count());
            String rd = movies.getRelease_date();
            String status = movies.getStatus();
            String tagline = movies.getTagline();
            String popular = Double.toString(movies.getPopularity());
            String img_path = movies.getPoster_path().toString();
            String img_path2 = movies.getBackdrop_path().toString();
            String cover_path = Const.IMAGE_URL + img_path;
            String backdrop_path = Const.IMAGE_URL + img_path2;
            Chip chip;
            for (int i = 0; i < movies.getGenres().size(); i++) {
                chip = new Chip(chipGroup.getContext());
                chip.setChipEndPadding(2);
                chip.setText(movies.getGenres().get(i).getName());
                chipGroup.addView(chip);
            }
            txt_tagline_md_frag.setText(tagline);
            txt_popular_md_frag.setText("Popularity: "+popular);
            txt_show_md_frag.setText(title);
            txt_rate_md_frag.setText("Vote Avg: " + rate +" | Vote: " + vote);
            txt_desc_md_frag.setText(desc);
            txt_rd_md_frag.setText("Release Date: " + rd);
            txt_status_md_frag.setText("Status: " + status);
            Glide.with(getActivity()).load(cover_path).into(cover_md_frag);
            Glide.with(getActivity()).load(backdrop_path).into(backdrop_md_frag);
            for (int i = 0; i < movies.getProduction_companies().size(); i++) {
                ImageView image = new ImageView(llh_image_md_frag.getContext());
                String prodlogo = Const.IMAGE_URL + movies.getProduction_companies()
                        .get(i)
                        .getLogo_path();
                String prodname = movies.getProduction_companies()
                        .get(i)
                        .getName();
                if (movies.getProduction_companies()
                        .get(i)
                        .getLogo_path() == null){
                    image.setImageDrawable(getResources().getDrawable(R.drawable.default_image, getActivity().getTheme()));
                }else if (prodlogo != "https://image.tmdb.org/3/t/p/w500/null"){
                    Glide.with(getActivity()).load(prodlogo).into(image);
                }
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(250, 250);
                lp.setMargins(30, 0, 30, 0);
                image.setLayoutParams(lp);
                llh_image_md_frag.addView(image);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar sb = Snackbar.make(view, prodname, Snackbar.LENGTH_SHORT);
                        sb.setAnchorView(R.id.bottomnav_mainmenu);
                        sb.show();
                    }
                });
            }
            if (movies != null){
                dialog.dismiss();
            }
        }
    };
}