package id.undika.uasmobile_005;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class dataDB {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public dataDB() {
    }

    private interface callbackFB {
        void onCallBack(List<String> listNama, List<String> listHari, List<String> listKelas, List<String> listWaktu);
    }

    private void readData(callbackFB callbackFB) {
        ArrayList<String> dbListNama = new ArrayList<>();
        ArrayList<String> dbListWaktu = new ArrayList<>();
        ArrayList<String> dbListHari = new ArrayList<>();
        ArrayList<String> dbListKelas = new ArrayList<>();

        db.collection("jadwal").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("Res", "Success: " + document.getData());
                        dbListNama.add(document.get("namaMK").toString());
                        dbListHari.add(document.get("hariMK").toString());
                        dbListKelas.add(document.get("kelasMK").toString());
                        dbListWaktu.add(document.get("waktuMK").toString());
                    }
                    Log.d("Res", "Success: " + dbListNama);
                    callbackFB.onCallBack(dbListNama, dbListHari, dbListKelas, dbListWaktu);
                } else {
                    Log.w("Res", "Error", task.getException());
                }

            }
        });
    }

    void fetchDB() {
        readData(new callbackFB() {
            @Override
            public void onCallBack(List<String> listNama, List<String> listHari, List<String> listKelas, List<String> listWaktu) {
                Log.d("Tag", "Callback" + listHari.size());
            }
        });
    }
}
