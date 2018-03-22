package ordonez.roger.bitacoraactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BitacoraActivity extends AppCompatActivity {
    private ArrayList<BitacoraItem> items;
    private ListView list;
    private AdapterBitacoraItem adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);
        items = new ArrayList<>();
        PopulateItem("soc jo","12:25","12/12/2018");
//TODO:afegir items a la llista
        //TODO: omplir el adapter amb la llista
        //TODO: crear el activity nou per editar
        //TODO: funcionalitats
    }

    public void PopulateItem(String tasca,String hora,String diamesany){
        items.add(new BitacoraItem(tasca,hora,diamesany));
    }
}
