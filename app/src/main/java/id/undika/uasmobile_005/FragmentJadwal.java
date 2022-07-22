package id.undika.uasmobile_005;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import id.undika.uasmobile_005.adapter.adapterJadwal;
import id.undika.uasmobile_005.model.modelJadwal;

public class FragmentJadwal extends Fragment {
    String tanggal = "";
    TextView tanggalView;

    RecyclerView rvJadwal;
    FloatingActionButton fabAdd;
    ArrayList<String> listId = new ArrayList<>();
    ArrayList<String> listNama = new ArrayList<>();
    ArrayList<String> listWaktu = new ArrayList<>();
    ArrayList<String> listHari = new ArrayList<>();
    ArrayList<String> listKelas = new ArrayList<>();
    private ArrayList<modelJadwal> listJadwal = new ArrayList<>();
    private modelJadwal modelJadwal;
    private adapterJadwal adapterJd;
    FirebaseFirestore db;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public FragmentJadwal() {
        // Required empty public constructor
    }

    public static FragmentJadwal newInstance(String param1, String param2) {
        FragmentJadwal fragment = new FragmentJadwal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_jadwal_rv, container, false);
        rvJadwal = view.findViewById(R.id.rv_jadwal);
        tanggalView = view.findViewById(R.id.tanggalJadwal);
        rvJadwal.setHasFixedSize(true);

        tanggalView.setText(tanggal);
        listJadwal.addAll(getListJadwal());
        showRecyclerViewJadwal();

        return view;
    }

    private void showRecyclerViewJadwal() {
        rvJadwal.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterJd = new adapterJadwal(this.getContext(), "fragJadwal");
        adapterJd.setListJadwal(listJadwal);
        rvJadwal.setAdapter(adapterJd);
    }

    private ArrayList<modelJadwal> getListJadwal() {
        Log.d("A", "getListDataJadwal: "+listNama.size());
        String[] namaMK = listNama.toArray(new String[listNama.size()]);
        String[] waktuMK = listWaktu.toArray(new String[listNama.size()]);
        String[] hariMK = listHari.toArray(new String[listNama.size()]);
        String[] kelasMK = listKelas.toArray(new String[listNama.size()]);

        ArrayList<modelJadwal> listJadwal = new ArrayList<>();

        for (int i = 0; i < namaMK.length; i++) {
            modelJadwal jm = new modelJadwal();
            jm.setNamaMK(namaMK[i]);
            jm.setWaktuMK(waktuMK[i]);
            jm.setKelas(kelasMK[i]);
            jm.setHariMK(hariMK[i]);
            listJadwal.add(jm);
        }
        return listJadwal;
    }
}