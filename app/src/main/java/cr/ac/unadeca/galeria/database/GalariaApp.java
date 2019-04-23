package cr.ac.unadeca.galeria.database;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
public class GalariaApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Inicializa la BD
        FlowManager.init(this);
    }
}
