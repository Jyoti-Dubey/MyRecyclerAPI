package recyclerapi.jyoti.com.myrecyclerapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import recyclerapi.jyoti.com.myrecyclerapi.R;
import recyclerapi.jyoti.com.myrecyclerapi.model.MainActivityModel;

/**
 * Created by jyoti on 15-04-2018.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.RecyclerViewHolder> {
    private final String TAG = "MainActivityAdapter";
    private ArrayList<MainActivityModel> arrayList = new ArrayList<MainActivityModel>();
    private final Context context;

    public MainActivityAdapter(Context context, ArrayList<MainActivityModel> arrayList) {

        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_child_layout, parent, false);
        RecyclerViewHolder recyclerviewholder = new RecyclerViewHolder(view);
        return recyclerviewholder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        MainActivityModel mainActivityModel = arrayList.get(position);

        holder.title.setText(mainActivityModel.getTitle());
        holder.desc.setText(mainActivityModel.getDesc());
        holder.publish.setText(mainActivityModel.getPublish());
       // holder.urlReadMore.setText(mainActivityModel.getUrlReadMore());

        Glide.with(context).
                load(mainActivityModel.getImgURL()).
                thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.imgURL);

        final String url = mainActivityModel.getUrlReadMore();
        holder.urlReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String TAG = "RecyclerViewHolder";
        private TextView title;
        private TextView desc;
        private TextView publish;
        private TextView urlReadMore;
        private ImageView imgURL;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            publish = itemView.findViewById(R.id.publish);
            urlReadMore = itemView.findViewById(R.id.urlReadMore);
            imgURL = itemView.findViewById(R.id.imgURL);

            itemView.setOnClickListener(this);
        }//end of  RecyclerViewHolder method

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Item Position = " + getPosition(), Toast.LENGTH_SHORT).show();

        }
    }
}
