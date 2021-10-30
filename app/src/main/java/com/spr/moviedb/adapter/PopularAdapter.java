package com.spr.moviedb.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spr.moviedb.R;
import com.spr.moviedb.helper.Const;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.model.Popular;
import com.spr.moviedb.model.UpComing;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.CardViewViewHolder> {

    private Context context;
    private List<Popular.Results> listPop;
    private List<Popular.Results> getListPop(){
        return listPop;
    }
    public void setListPop(List<Popular.Results> listPop){
        this.listPop = listPop;
    }
    public PopularAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public PopularAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing
                ,parent
                ,false);
        return new PopularAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.CardViewViewHolder holder, int position) {
        final Popular.Results results = getListPop().get(position);
        holder.lbl_title_cv.setText(results.getTitle());
        holder.lbl_overview_cv.setText(results.getOverview());
        holder.lbl_rd_cv.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMAGE_URL + results.getPoster_path())
                .into(holder.imageposter_cv);
        holder.cv_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, MovieDetailsActivity.class);
//                intent.putExtra("movie_id", ""+results.getId());
//                context.startActivity(intent);

                Bundle bundle = new Bundle();
                bundle.putString("movie_id",""+results.getId());
                Navigation.findNavController(view).navigate(R.id.action_popularFragment_to_movieDetailFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListPop().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        private TextView lbl_title_cv,lbl_overview_cv, lbl_rd_cv;
        private ImageView imageposter_cv;
        private CardView cv_cv;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_cv = itemView.findViewById(R.id.cv_cv);
            lbl_title_cv = itemView.findViewById(R.id.lbl_title_cv);
            lbl_overview_cv = itemView.findViewById(R.id.lbl_overview_cv);
            lbl_rd_cv = itemView.findViewById(R.id.lbl_rd_cv);
            imageposter_cv = itemView.findViewById(R.id.imageposter_cv);
        }
    }
}
