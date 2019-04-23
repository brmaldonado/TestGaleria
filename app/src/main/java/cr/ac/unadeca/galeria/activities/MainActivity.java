package cr.ac.unadeca.galeria.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import cr.ac.unadeca.galeria.R;
import cr.ac.unadeca.galeria.database.models.Imagenes;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista;
    private LinearLayout formulario;
    private CoordinatorLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        formulario=findViewById(R.id.formulario);
        view = findViewById(R.id.coordinador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialog();
                establecerAdaptador();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        establecerAdaptador();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


        
    private void establecerAdaptador(){
        lista.setAdapter(new CustomAdapterRecycler(getImagenes(), this, view));
    }

    private List<Imagenes> getImagenes(){
        return SQLite.select().from(Imagenes.class).queryList();
    }

    private void testBaseDatos() {
        Delete.table(Imagenes.class);
        Imagenes imagen;
        for (int a = 0; a < 10; a++) {
            imagen = new Imagenes();
            imagen.imagen = "https://www.imagen.com.mx/assets/img/imagen_share.png";
            imagen.descripcion = "Descripcion de prueba " + (a + 1);
            imagen.titulo = "Titulo de prueba" + (a + 1);
            imagen.save();
        }
    }
        public void mostrarDialog(){
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View formulario = layoutInflater.inflate(R.layout.formulario, null);

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(formulario);
            builder.setMessage("Rellene toda la información solicitada")
                    .setTitle("Agregar Imagen")
                    .setCancelable(false)
                    //Al hacer clic en enviar muestra un mensaje al usuario de confirmacion
                    .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "La imagen se ha agregado", Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialogo = builder.create();

            dialogo.show();
        }

    private void validate(TextInputLayout u, TextInputLayout t, TextInputLayout d) throws Exception {
        if (u.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Debe llenar el campo URL");
        }
        if (t.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Debe llenar el campo NOMBRE DE LA IMAGEN");
        }
        if (d.getEditText().getText().toString().isEmpty()) {
            throw new Exception("Debe llenar el campo descripción");
        }
    }
    //Método que permite guardar la informacion y crear el registro de un nuevo arbolito
    //Hace la conexion con la base de datos para poder almacenarlos alli
    private void guardarBD(TextInputLayout t, TextInputLayout d, Imagenes imagen) {
        imagen.titulo = t.getEditText().getText().toString();
        imagen.descripcion = d.getEditText().getText().toString();
        imagen.save();

        //Muestra el mensaje que confirma que los datos se han guardado
        Snackbar.make(view, "Se ha guardado la imagen", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

            notifyAll();


    }


}
