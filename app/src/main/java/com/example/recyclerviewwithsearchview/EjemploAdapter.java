package com.example.recyclerviewwithsearchview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.recyclerviewwithsearchview.databinding.RecyclerviewBinding;

import java.util.Comparator;
import java.util.List;

public class EjemploAdapter extends RecyclerView.Adapter<EjemploViewHolder>
{

    private final SortedList.Callback<EjemploModel> mCallback = new SortedList.Callback<EjemploModel>()
    {
        @Override
        public int compare(EjemploModel modelo1, EjemploModel modelo2)
        {
            return mComparador.compare(modelo1, modelo2);
        }

        /**
         * The purpose of this method is to determine if the content of a model has changed.
         * The SortedList uses this to determine if a change event needs to be invoked
         */
        @Override
        public boolean areContentsTheSame(EjemploModel oldItem, EjemploModel newItem)
        {
            return oldItem.equals(newItem);
        }

        /**
         * Este método es utilizado para saber si un elemnto ya existe en List y si
         * una animación de agregado, movimiento  o cambio debe de ser desplegada.
         * @param item1 Modelo 1
         * @param item2 Modelo 2
         * @return Si son iguales true, de otra forma false
         */
        @Override
        public boolean areItemsTheSame(EjemploModel item1, EjemploModel item2)
        {
            return item1.getId() == item2.getId();
        }

        @Override
        public void onInserted(int position, int count)
        {
            notifyItemRangeChanged(position,count);
        }

        @Override
        public void onChanged(int position, int count)
        {
            notifyItemRangeChanged(position,count);
        }

        @Override
        public void onRemoved(int position, int count)
        {
            notifyItemRangeRemoved(position,count);
        }

        @Override
        public void onMoved(int position, int count)
        {
            notifyItemMoved(position,count);
        }
    };
    private final SortedList<EjemploModel> mSortedList = new SortedList<>(EjemploModel.class, mCallback);
    private final LayoutInflater mInflater;
    private final Comparator<EjemploModel> mComparador;

    public EjemploAdapter(Context context, Comparator<EjemploModel> comparator)
    {
        mInflater = LayoutInflater.from(context);
        mComparador = comparator;
    }


    @Override
    public EjemploViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final RecyclerviewBinding binding = RecyclerviewBinding.inflate(mInflater, parent,false );
        return  new EjemploViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EjemploViewHolder ejemploViewHolder, int position)
    {
        final EjemploModel model = mSortedList.get(position);
        ejemploViewHolder.setmBinding(model);
    }

    @Override
    public int getItemCount()
    {
        return mSortedList.size();
    }

    public void add(EjemploModel model) {
        mSortedList.add(model);
    }

    public void remove(EjemploModel model) {
        mSortedList.remove(model);
    }

    public void add(List<EjemploModel> models) {
        mSortedList.addAll(models);
    }

    public void remove(List<EjemploModel> models) {

        // Quita los elementos uno por uno

        // Utilizando este método, el performance mejora
        mSortedList.beginBatchedUpdates();
        for (EjemploModel model : models) {
            mSortedList.remove(model);
        }

        // Al utilizar este método, RecyclerView es notificado de los cambios y se actualizará
        mSortedList.endBatchedUpdates();
    }

    public void replaceAll(List<EjemploModel> models) {

        mSortedList.beginBatchedUpdates();
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final EjemploModel model = mSortedList.get(i);
            if (!models.contains(model)) {
                mSortedList.remove(model);
            }
        }
        mSortedList.addAll(models);
        mSortedList.endBatchedUpdates();
    }
}
