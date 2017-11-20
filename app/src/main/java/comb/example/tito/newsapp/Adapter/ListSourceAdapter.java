package comb.example.tito.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.telecom.RemoteConference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import comb.example.tito.newsapp.Common.Common;
import comb.example.tito.newsapp.Interface.IconIdeaService;
import comb.example.tito.newsapp.Interface.ItemClickListener;
import comb.example.tito.newsapp.List_News;
import comb.example.tito.newsapp.MainActivity;
import comb.example.tito.newsapp.Model.IconBetterIdea;
import comb.example.tito.newsapp.Model.Website;
import comb.example.tito.newsapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Tito on 18/10/2017.
 */

class ListSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    ItemClickListener itemClickListener;
    TextView textView;
    ImageView circleImageView;


    public ListSourceViewHolder(View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.source_name);
        circleImageView=(ImageView) itemView.findViewById(R.id.source_image);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false );
    }
}
public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder>{
   private Context context;
    private Website website;
    private IconIdeaService iconIdeaService;


    public ListSourceAdapter(Context context, Website website) {
        this.context = context;
        this.website = website;
        iconIdeaService=Common.getIconService();
    }

    @Override
    public int getItemCount() {
        return website.getSources().size();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.source_layout,parent,false);
     return new ListSourceViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder,  final int position) {
        StringBuilder stringBuilder=new StringBuilder("http://icons.better-idea.org/allicons.json?url=");
        iconIdeaService.getIconUrl(stringBuilder.append(website.getSources().get(position).getUrl()).toString()).enqueue(new Callback<IconBetterIdea>() {
            @Override
            public void onResponse(Response<IconBetterIdea> response, Retrofit retrofit) {


                try {

//          Picasso.with(context).load(response.body().getIcons().get(0).getUrl()).into(holder.circleImageView);
                }catch (Exception e)

                {}
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("tito",  "failed");
            }
        });

        holder.textView.setText(website.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
              //  Log.d("tito",position+"");
                Intent intent=new Intent(context, List_News.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);     //Error if not added
                intent.putExtra("source",website.getSources().get(position).getId());
                Log.d("tito",website.getSources().get(position).getId()+"");
                intent.putExtra("sortBy",website.getSources().get(position).getSortBysAvailable().get(0));
                Log.d("tito",website.getSources().get(position).getSortBysAvailable().get(0)+"");
                context.startActivity(intent);

            }
        });

    }
}
