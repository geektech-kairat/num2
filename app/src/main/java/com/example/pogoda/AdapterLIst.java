package com.example.pogoda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdapterLIst extends RecyclerView.Adapter<AdapterLIst.ViewHolderList> {

    List<ListItemModel> list = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolderList(view);
    }

    public AdapterLIst(List<ListItemModel> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderList holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(ListItemModel listItemModel) {
        list.add(listItemModel);
        notifyDataSetChanged();
    }

    public void addList2(List<ListItemModel> list2) {
        list2.addAll(list2);
        notifyDataSetChanged();
    }
    public void sortListBank(){
        Collections.sort(list, (o1, o2) -> o1.getNameBank().compareTo(o2.getNameBank()));
        notifyDataSetChanged();
    }
    public void sortListBye(){
    }


    class ViewHolderList extends RecyclerView.ViewHolder {

        private TextView nameBank;
        private TextView bye;
        private TextView sale;



        public ViewHolderList(@NonNull View itemView) {
            super(itemView);
            nameBank = itemView.findViewById(R.id.nameBank);
            bye = itemView.findViewById(R.id.bye);
            sale = itemView.findViewById(R.id.sale);




        }

        public void onBind(ListItemModel listItemModel) {
            nameBank.setText(listItemModel.nameBank);
            bye.setText(listItemModel.bye);
            sale.setText(listItemModel.sale);


        }
    }
}
