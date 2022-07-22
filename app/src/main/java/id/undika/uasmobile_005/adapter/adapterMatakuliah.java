package id.undika.uasmobile_005.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import id.undika.uasmobile_005.R;
import id.undika.uasmobile_005.UpdateDeleteActivity;
import id.undika.uasmobile_005.model.modelJadwal;
import id.undika.uasmobile_005.model.modelMatakuliah;

public class adapterMatakuliah extends RecyclerView.Adapter<adapterMatakuliah.ViewHolder>{
    private Context context;
    private static ArrayList<modelJadwal> listJadwal;
    private String location;

    public adapterMatakuliah(Context context, String location) {
        this.context = context;
        this.location = location;
    }
    public Context getContext() {
        return context;
    }

    public static ArrayList<modelJadwal> getListJadwal() {
        return listJadwal;
    }

    public static void setListMatakuliah(ArrayList<modelJadwal> listJadwal) {
        adapterJadwal.listJadwal = listJadwal;
    }


    @NonNull
    @Override
    public adapterMatakuliah.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.activity_detail_mk, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterMatakuliah.ViewHolder holder, int position) {
        modelJadwal jm = listJadwal.get(position);

        holder.outputNamaMK.setText(jm.getNamaMK());
        holder.outputJamMK.setText(jm.getWaktuMK());
        holder.outputHariMK.setText(jm.getHariMK());
        holder.outputKelasMK.setText(jm.getKelas());
    }

    @Override
    public int getItemCount() {
        return getListJadwal().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView outputNamaMK, outputJamMK, outputHariMK, outputKelasMK;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            outputNamaMK = itemView.findViewById(R.id.nama_mk);
            outputJamMK = itemView.findViewById(R.id.jam_mk);
            outputHariMK = itemView.findViewById(R.id.hari_mk);
            outputKelasMK = itemView.findViewById(R.id.kelas_mk);

            if (location.equals("fragJadwal")) {
                itemView.setOnClickListener(view -> {
                    int i = getAdapterPosition();

                    modelJadwal jm = getListJadwal().get(i);
                    jm.setNamaMK(jm.getNamaMK());
                    jm.setHariMK(jm.getHariMK());
                    jm.setKelas(jm.getKelas());
                    jm.setWaktuMK(jm.getWaktuMK());
                    jm.setId(jm.getId());

                    Intent detailIntent = new Intent(itemView.getContext(), UpdateDeleteActivity.class);
                    detailIntent.putExtra(UpdateDeleteActivity.EXTRA_MATKUL, jm);

                    context.startActivity(detailIntent);
                });
            }
        }
    }
}
