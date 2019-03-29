package com.example.recyclerviewwithsearchview;

import android.support.v7.widget.RecyclerView;

import com.example.recyclerviewwithsearchview.databinding.RecyclerviewBinding;

public class EjemploViewHolder extends RecyclerView.ViewHolder
{
    RecyclerviewBinding mBinding;

    public EjemploViewHolder(RecyclerviewBinding _binding)
    {
        super(_binding.getRoot());
        this.mBinding = _binding;
    }

    public void setmBinding(EjemploModel model)
    {
        mBinding.setModel(model);
    }

}