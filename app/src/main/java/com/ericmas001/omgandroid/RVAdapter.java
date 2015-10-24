package com.ericmas001.omgandroid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PokemonViewHolder>{
    List<Pokemon> pokemons;

    RVAdapter(List<Pokemon> pokemons){
        this.pokemons = pokemons;
    }
    @Override
    public int getItemCount() {
        return pokemons.size();
    }
    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new PokemonViewHolder(v);
    }
    @Override
    public void onBindViewHolder(PokemonViewHolder personViewHolder, int i) {
        personViewHolder.pokemonName.setText(pokemons.get(i).name);
        personViewHolder.pokemonType.setText(pokemons.get(i).type);
        personViewHolder.pokemonPhoto.setImageResource(pokemons.get(i).photoId);
    }
    @Override
     public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView pokemonName;
        TextView pokemonType;
        ImageView pokemonPhoto;

        PokemonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            pokemonName = (TextView)itemView.findViewById(R.id.pokemon_name);
            pokemonType = (TextView)itemView.findViewById(R.id.pokemon_type);
            pokemonPhoto = (ImageView)itemView.findViewById(R.id.pokemon_photo);
        }
    }

}
