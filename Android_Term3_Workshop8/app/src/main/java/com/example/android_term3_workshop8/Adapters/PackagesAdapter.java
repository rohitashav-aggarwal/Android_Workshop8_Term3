package com.example.android_term3_workshop8.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_term3_workshop8.R;
import com.example.android_term3_workshop8.models.Packages;
import java.util.Date;
import java.util.List;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.PackagesViewHolder> {

    private Context mContext;
    private List<Packages> packageList;

    public PackagesAdapter(Context mContext, List<Packages> packageList) {
        this.mContext = mContext;
        this.packageList = packageList;
    }

    @NonNull
    @Override
    public PackagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_packages, parent, false);
        return new PackagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackagesViewHolder holder, int position) {
        Packages packages = packageList.get(position);
        Date startDate = new Date(packages.getPkgStartDate().getTime());
        Date endDate = new Date(packages.getPkgEndDate().getTime());
        holder.textViewPackageName.setText(packages.getPkgName());
        holder.textViewPackageDescription.setText(packages.getPkgDesc()
                + "\n" + "Base Price :$" + packages.getPkgBasePrice()
                + "\n" + "Start Date :" + startDate
                + "\n" + "End Date :" + endDate);

    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }

    class PackagesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPackageName, textViewPackageDescription;

        public PackagesViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPackageName = itemView.findViewById(R.id.textViewPackageName);
            textViewPackageDescription = itemView.findViewById(R.id.textViewPackageDescription);


        }
    }
}
