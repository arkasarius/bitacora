package ordonez.roger.bitacoraactivity;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by roger on 22/03/2018.
 */

public class AdapterBitacoraItem extends ArrayAdapter<BitacoraItem> {
        public AdapterBitacoraItem(@NonNull Context context, @LayoutRes int resource, @NonNull List<BitacoraItem> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // 1. Crear un nou View si és necessari (no cal si convertView no és null)
            View root = convertView; // arrel d'un item de la llista
            if (root == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                root = inflater.inflate(R.layout.activity_bitacor_item, parent, false);
            }


/*
            CheckBox checkBox = (CheckBox) root.findViewById(R.id.checkBox);
            ShoppingItem item = getItem(position);
            checkBox.setText(item.getText());
            checkBox.setChecked(item.isChecked());
*/
            return root;
        }
    }

