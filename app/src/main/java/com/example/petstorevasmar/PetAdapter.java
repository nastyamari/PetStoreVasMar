package com.example.petstorevasmar;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {
    //private final static String PHOTO_URL = "https://services.hanselandpetal.com/photos/";
    private List<PetStore> mFlowers;
    private Context mContext;

    PetAdapter(List<PetStore> flowers) {
        this.mFlowers = flowers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PetStore flower = mFlowers.get(position);
        holder.nameTextView.setText(flower.getName());
        /*
        Picasso.with(mContext)
                .load(flower.getPhotoUrls())
                .resize(200, 150)
                .into(holder.flowerImageView);
*/
    }

    @Override
    public int getItemCount() {
        if (mFlowers == null) {
            return 0;
        }
        return mFlowers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView flowerImageView;
        TextView categoryTV;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
           // flowerImageView = (ImageView) itemView.findViewById(R.id.itemImageView);
        }
    }
}
