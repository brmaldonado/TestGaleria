package cr.ac.unadeca.galeria.subclases;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cr.ac.unadeca.galeria.R;

public class ImagenViewHolder extends RecyclerView.ViewHolder {

    public TextView titulo;
    public TextView descripcion;
    public TextView comentarios;
    public ImageView imagen;

    public ImagenViewHolder(@NonNull View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.titulo);
        descripcion = itemView.findViewById(R.id.descripcion);
        comentarios = itemView.findViewById(R.id.comentarios);
        imagen = itemView.findViewById(R.id.imagen);
    }
}
