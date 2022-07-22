package id.undika.uasmobile_005;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firestore.v1.FirestoreGrpc;

import java.util.ArrayList;
import java.util.List;

import id.undika.uasmobile_005.adapter.adapterJadwal;
import id.undika.uasmobile_005.adapter.adapterMatakuliah;
import id.undika.uasmobile_005.model.modelJadwal;
import id.undika.uasmobile_005.model.modelMatakuliah;


public class FragmentMK extends Fragment {
    ArrayList<String> listId = new ArrayList<>();
    ArrayList<String> listNama = new ArrayList<>();
    ArrayList<String> listWaktu = new ArrayList<>();
    ArrayList<String> listHari = new ArrayList<>();
    ArrayList<String> listKelas = new ArrayList<>();

    private ArrayList<modelJadwal> listJadwal = new ArrayList<>();
    private adapterJadwal adapterJd;
    FloatingActionButton fabAdd;
    RecyclerView rvDaftar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public FragmentMK() {
        // Required empty public constructor
    }

    public static FragmentMK newInstance(String param1, String param2) {
        FragmentMK fragment = new FragmentMK();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_mk_rv, container, false);
        rvDaftar = view.findViewById(R.id.rv_mk);
        rvDaftar.setHasFixedSize(true);

        listJadwal.addAll(getListJadwal());
        showRecyclerViewMatakuliah();

        fabAdd = view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(view1 -> startActivity(new Intent(this.getActivity(), InputMK.class)));


        return view;
    }

    private void showRecyclerViewMatakuliah() {
        rvDaftar.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterJd = new adapterJadwal(this.getContext(), "fragDaftar");
        adapterJd.setListJadwal(listJadwal);
        rvDaftar.setAdapter(adapterJd);
    }

    private ArrayList<modelJadwal> getListJadwal() {
        Log.d("A", "getListDataJadwal: "+listNama.size());
        String[] namaMK = listNama.toArray(new String[listNama.size()]);
        String[] waktuMK = listWaktu.toArray(new String[listNama.size()]);
        String[] hariMK = listHari.toArray(new String[listNama.size()]);
        String[] kelasMK = listKelas.toArray(new String[listNama.size()]);
        String[] idMK = listId.toArray(new String[listNama.size()]);

        ArrayList<modelJadwal> listJadwal = new ArrayList<>();

        for (int i = 0; i < namaMK.length; i++) {
            modelJadwal jm = new modelJadwal();
            jm.setNamaMK(namaMK[i]);
            jm.setWaktuMK(waktuMK[i]);
            jm.setKelas(kelasMK[i]);
            jm.setHariMK(hariMK[i]);
            jm.setId(idMK[i]);
            listJadwal.add(jm);
        }
        return listJadwal;
    }
}