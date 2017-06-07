package com.kerse.markproject.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kerse.markproject.R;
import com.kerse.markproject.activity.ProfileActivity;
import com.kerse.markproject.common.CircleTransform;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.fragment.MarkFragment;
import com.kerse.markproject.model.CollectMarkAndPersons;
import com.kerse.markproject.model.CollectedPersonList;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 12.5.2017.
 */

public class CollectedPersonListAdapter extends RecyclerView.Adapter<CollectedPersonListAdapter.ViewHolder> {
    List<CollectMarkAndPersons> collectMarkAndPersonsList = new ArrayList<>();
    public Context context;
    Button showProfile;

    public CollectedPersonListAdapter(List<CollectMarkAndPersons> collectMarkAndPersonsList, Context context) {

        this.context = context;
        this.collectMarkAndPersonsList = collectMarkAndPersonsList;
    }

    @Override
    public CollectedPersonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_cell, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollectedPersonListAdapter.ViewHolder holder, final int position) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        Picasso.with(context)
                //  .load(Config.SERVER_URL+collectMarkAndPersonsList.get(position).getPerson().getProfilePic())
                .load(R.drawable.aaaa)
                .transform(new CircleTransform())
                .into(holder.personImg);
        int point = collectMarkAndPersonsList.get(position).getPerson().getPopularPoint();
        int collectMarkCount = collectMarkAndPersonsList.get(position).getMarkCount();
        Picasso.with(context)
                .load(getImg(collectMarkCount, point,collectMarkAndPersonsList.get(position).isMsgPermision()))

                .into(holder.popularityImg);
        holder.nameAndSurname.setText(collectMarkAndPersonsList.get(position).getPerson().getFirstName() + " " + collectMarkAndPersonsList.get(position).getPerson().getLastName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("Uid", collectMarkAndPersonsList.get(position).getPerson().getUniqueID());
                intent.putExtra("msgPer",collectMarkAndPersonsList.get(position).isMsgPermision());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (collectMarkAndPersonsList != null ? collectMarkAndPersonsList.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView personImg;
        public ImageView popularityImg;
        public TextView nameAndSurname;
        public View itemView;
        public RelativeLayout cellRl;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cellRl = (RelativeLayout) itemView.findViewById(R.id.cellRl);
            personImg = (ImageView) itemView.findViewById(R.id.imgProfile);
            nameAndSurname = (TextView) itemView.findViewById(R.id.tvNameAndSurname);
            popularityImg = (ImageView) itemView.findViewById(R.id.imgStatus);

        }
    }

    boolean msgValid = false;

    private int getImg(int collectMarkCount, int point,boolean msgPer) {
        if(!msgPer){
        if (point <= 10) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel3_1;
                case 2:
                    return R.drawable.izlevel3_2;
                case 3:
                    return R.drawable.izlevel3;
                default:
                    return R.drawable.izlevel3;
            }
        } else if (point <= 20) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel4_1;

                case 2:
                    return R.drawable.izlevel4_2;

                case 3:
                    return R.drawable.izlevel4_3;

                case 4:
                    return R.drawable.izlevel4;

                default:
                    return R.drawable.izlevel4;

            }
        } else if (point <= 30) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel5_1;

                case 2:
                    return R.drawable.izlevel5_2;

                case 3:
                    return R.drawable.izlevel5_3;

                case 4:
                    return R.drawable.izlevel5_4;

                case 5:
                    return R.drawable.izlevel5;

                default:
                    return R.drawable.izlevel5;

            }

        } else if (point <= 40) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel6_1;

                case 2:
                    return R.drawable.izlevel6_2;

                case 3:
                    return R.drawable.izlevel6_3;

                case 4:
                    return R.drawable.izlevel6_4;

                case 5:
                    return R.drawable.izlevel6_5;

                case 6:
                    return R.drawable.izlevel6;

                default:
                    return R.drawable.izlevel6;

            }

        } else if (point <= 50) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel7_1;

                case 2:
                    return R.drawable.izlevel7_2;

                case 3:
                    return R.drawable.izlevel7_3;

                case 4:
                    return R.drawable.izlevel7_4;

                case 5:
                    return R.drawable.izlevel7_5;

                case 6:
                    return R.drawable.izlevel7_6;

                case 7:
                    return R.drawable.izlevel7;

                default:
                    return R.drawable.izlevel7;

            }

        } else if (point <= 60) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel8_1;

                case 2:
                    return R.drawable.izlevel8_2;

                case 3:
                    return R.drawable.izlevel8_3;

                case 4:
                    return R.drawable.izlevel8_4;

                case 5:
                    return R.drawable.izlevel8_5;

                case 6:
                    return R.drawable.izlevel8_6;

                case 7:
                    return R.drawable.izlevel8_7;

                case 8:
                    return R.drawable.izlevel8;

                default:
                    return R.drawable.izlevel8;

            }

        } else if (point <= 70) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel9_1;

                case 2:
                    return R.drawable.izlevel9_2;

                case 3:
                    return R.drawable.izlevel9_3;

                case 4:
                    return R.drawable.izlevel9_4;

                case 5:
                    return R.drawable.izlevel9_5;

                case 6:
                    return R.drawable.izlevel9_6;

                case 7:
                    return R.drawable.izlevel9_7;

                case 8:
                    return R.drawable.izlevel9_8;

                case 9:
                    return R.drawable.izlevel9;

                default:
                    return R.drawable.izlevel9;

            }

        } else if (point <= 80) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel10_1;

                case 2:
                    return R.drawable.izlevel10_2;

                case 3:
                    return R.drawable.izlevel10_3;

                case 4:
                    return R.drawable.izlevel10_4;

                case 5:
                    return R.drawable.izlevel10_5;

                case 6:
                    return R.drawable.izlevel10_6;

                case 7:
                    return R.drawable.izlevel10_7;

                case 8:
                    return R.drawable.izlevel10_8;

                case 9:
                    return R.drawable.izlevel10_9;

                case 10:
                    return R.drawable.izlevel10;

                default:
                    return R.drawable.izlevel10;

            }

        } else if (point <= 90) {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel11_1;

                case 2:
                    return R.drawable.izlevel11_2;

                case 3:
                    return R.drawable.izlevel11_3;

                case 4:
                    return R.drawable.izlevel11_4;

                case 5:
                    return R.drawable.izlevel11_5;

                case 6:
                    return R.drawable.izlevel11_6;

                case 7:
                    return R.drawable.izlevel11_7;

                case 8:
                    return R.drawable.izlevel11_8;

                case 9:
                    return R.drawable.izlevel11_9;

                case 10:
                    return R.drawable.izlevel11_10;

                case 11:
                    return R.drawable.izlevel11;

                default:
                    return R.drawable.izlevel11;

            }

        } else {
            switch (collectMarkCount) {
                case 1:
                    return R.drawable.izlevel12_1;

                case 2:
                    return R.drawable.izlevel12_2;

                case 3:
                    return R.drawable.izlevel12_3;

                case 4:
                    return R.drawable.izlevel12_4;

                case 5:
                    return R.drawable.izlevel12_5;

                case 6:
                    return R.drawable.izlevel12_6;

                case 7:
                    return R.drawable.izlevel12_7;

                case 8:
                    return R.drawable.izlevel12_8;

                case 9:
                    return R.drawable.izlevel12_9;

                case 10:
                    return R.drawable.izlevel12_10;

                case 11:
                    return R.drawable.izlevel12_11;

                case 12:
                    return R.drawable.izlevel12;

                default:
                    return R.drawable.izlevel12;

            }

        }
        }else{
            return R.drawable.ic_yesil_01;
        }
    }
}
