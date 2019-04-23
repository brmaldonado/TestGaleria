package cr.ac.unadeca.galeria.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import cr.ac.unadeca.galeria.R;
import cr.ac.unadeca.galeria.database.models.Imagenes;

public class CustomAdapter extends ArrayAdapter<Imagenes>{

    private List<Imagenes> dataSet;
    Context mContext;
    CoordinatorLayout view;

    private static class ViewHolder {
        TextView titulo;
        TextView descripcion;
        TextView comentarios;
        ImageView imagen;
    }

    public CustomAdapter(List<Imagenes> data, Context context, CoordinatorLayout l) {
        super(context, R.layout.imagen, data);
        this.dataSet = data;
        this.mContext=context;
        this.view = l;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Imagenes dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.imagen, parent, false);
            viewHolder.imagen =  convertView.findViewById(R.id.imagen);
            viewHolder.titulo =  convertView.findViewById(R.id.titulo);
            viewHolder.descripcion =  convertView.findViewById(R.id.descripcion);
            viewHolder.comentarios =  convertView.findViewById(R.id.comentarios);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        Glide.with(mContext).load(dataModel.imagen).error( Glide.with(mContext)
                .load(R.mipmap.ic_launcher)).fitCenter().into(viewHolder.imagen);
        viewHolder.titulo.setText(dataModel.titulo);
        viewHolder.descripcion.setText(dataModel.descripcion);
        viewHolder.comentarios.setText(dataModel.imagen);

        // Return the completed view to render on screen
        return convertView;

    }

}