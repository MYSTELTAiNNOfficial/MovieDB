package com.spr.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spr.moviedb.R;
import com.spr.moviedb.helper.Const;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.view.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingAdapter  extends RecyclerView.Adapter<NowPlayingAdapter.CardViewViewHolder> {

    private Context context;
    private List<NowPlaying.Results> listNp;
    private List<NowPlaying.Results> getListNp(){
        return listNp;
    }
    public void setListNp(List<NowPlaying.Results> listNp){
        this.listNp = listNp;
    }
    public NowPlayingAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public NowPlayingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing
                ,parent
                ,false);
        return new NowPlayingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final NowPlaying.Results results = getListNp().get(position);
        holder.lbl_title_np.setText(results.getTitle());
        holder.lbl_overview_np.setText(results.getOverview());
        holder.lbl_rd_np.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.COVER_URL + results.getPoster_path())
                .into(holder.imageposter_np);
        holder.cv_np.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie_id", ""+results.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNp.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        private TextView lbl_title_np,lbl_overview_np, lbl_rd_np;
        private ImageView imageposter_np;
        private CardView cv_np;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_np = itemView.findViewById(R.id.cv_np);
            lbl_title_np = itemView.findViewById(R.id.lbl_title_np);
            lbl_overview_np = itemView.findViewById(R.id.lbl_overview_np);
            lbl_rd_np = itemView.findViewById(R.id.lbl_rd_np);
            imageposter_np = itemView.findViewById(R.id.imageposter_np);
        }
    }
}
