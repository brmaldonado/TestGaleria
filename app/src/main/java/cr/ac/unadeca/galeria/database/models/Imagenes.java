package cr.ac.unadeca.galeria.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.galeria.database.GaleriaDB;

@Table(database = GaleriaDB.class)
public class Imagenes extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String imagen;

    @Column
    public String titulo;

    @Column
    public String descripcion;
}
