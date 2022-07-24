package id.undika.uasmobile_005.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import id.undika.uasmobile_005.UpdateDeleteActivity;
import id.undika.uasmobile_005.model.modelJadwal;
import id.undika.uasmobile_005.R;

public class adapterJadwal extends RecyclerView.Adapter<adapterJadwal.ViewHolder> {
    private Context context;
    public static ArrayList<modelJadwal> listJadwal;
    private int mSelectedNotedIndex;
    private String location;

    public adapterJadwal(Context cont, String loc) {
        context = cont;
        location = loc;
    }
    public Context getContext() {
        return context;
    }

    public static List<modelJadwal> getListJadwal() {
        return listJadwal;
    }

    public static void setListJadwal(ArrayList<modelJadwal> listJadwal) {
        adapterJadwal.listJadwal = listJadwal;
    }

    @NonNull
    @Override
    public adapterJadwal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.activity_detail_jadwal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterJadwal.ViewHolder holder, int position) {
        modelJadwal j = listJadwal.get(position);
        holder.outputNamaMK.setText(j.getNamaMK());
        holder.outputJamMK.setText(j.getWaktuMK());
        holder.outputHariMK.setText(j.getHariMK());
        holder.outputKelasMK.setText(j.getKelas());
    }

    @Override
    public int getItemCount() {
        return getListJadwal().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView outputNamaMK, outputJamMK, outputHariMK, outputKelasMK, outputIdMK;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            outputNamaMK = itemView.findViewById(R.id.nama_mk);
            outputJamMK = itemView.findViewById(R.id.jam_mk);
            outputHariMK = itemView.findViewById(R.id.hari_mk);
            outputKelasMK = itemView.findViewById(R.id.kelas_mk);

            if (location.equals("fragDaftar")) {
                itemView.setOnClickListener(view -> {
                    int i = getAdapterPosition();
                    modelJadwal jm = getListJadwal().get(i);

                    jm.setNamaMK(jm.getNamaMK());
                    jm.setHariMK(jm.getHariMK());
                    jm.setKelas(jm.getKelas());
                    jm.setWaktuMK(jm.getWaktuMK());
                    jm.setId_Doc(jm.getId_Doc());

                    Intent detailIntent = new Intent(itemView.getContext(), UpdateDeleteActivity.class);
                    detailIntent.putExtra(UpdateDeleteActivity.EXTRA_MATKUL, jm);

                    context.startActivity(detailIntent);

                });
            }
        }
    }
}
