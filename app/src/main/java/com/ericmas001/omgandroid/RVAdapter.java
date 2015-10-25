package com.ericmas001.omgandroid;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PokemonViewHolder>{
    List<Pokemon> pokemons;
    private SparseBooleanArray selectedItems;

    RVAdapter(List<Pokemon> pokemons){
        this.pokemons = pokemons;
        selectedItems = new SparseBooleanArray(getItemCount());
    }
    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PokemonViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false));
    }
    @Override
    public void onBindViewHolder(final PokemonViewHolder pokemonViewHolder, int i) {
        pokemonViewHolder.bind(pokemons.get(i));
    }
    @Override
     public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener  {
        CardView cv;
        TextView pokemonName;
        TextView pokemonType;
        ImageView pokemonPhoto;
        Pokemon pokemon;

        PokemonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            cv = (CardView)itemView.findViewById(R.id.cv);
            pokemonName = (TextView)itemView.findViewById(R.id.pokemon_name);
            pokemonType = (TextView)itemView.findViewById(R.id.pokemon_type);
            pokemonPhoto = (ImageView)itemView.findViewById(R.id.pokemon_photo);
        }

        public void bind(Pokemon p)
        {
            pokemon = p;
            pokemonName.setText(p.name);
            pokemonType.setText(p.type);
            pokemonPhoto.setImageBitmap(p.bmp);
            cv.setSelected(pokemon.selected);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), pokemon.name, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                         ContextMenu.ContextMenuInfo menuInfo) {

            Context c = itemView.getContext();
            menu.setHeaderTitle(pokemon.name);
            MenuItem threatActionItem = menu.add(c.getString(R.string.ctxt_threat));
            threatActionItem.setOnMenuItemClickListener(mOnThreatClickListener);
            MenuItem killActionItem = menu.add(c.getString(R.string.ctxt_kill));
            killActionItem.setOnMenuItemClickListener(mOnKillClickListener);

        }
        private final MenuItem.OnMenuItemClickListener mOnThreatClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Context c = itemView.getContext();
                Toast.makeText(c, String.format(c.getString(R.string.msg_happy),pokemon.name), Toast.LENGTH_LONG).show();
                return true;
            }
        };
        private final MenuItem.OnMenuItemClickListener mOnKillClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Context c = itemView.getContext();
                Toast.makeText(c, String.format(c.getString(R.string.msg_killed),pokemon.name), Toast.LENGTH_LONG).show();
                return true;
            }
        };
    }

}
