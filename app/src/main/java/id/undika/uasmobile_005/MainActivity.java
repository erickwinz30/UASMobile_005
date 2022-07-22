package id.undika.uasmobile_005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.undika.uasmobile_005.model.modelJadwal;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private BottomNavigationView navView;
    private List<modelJadwal> list;
    Fragment fragmentJadwal, fragmentMK, fragmentMe;
    FragmentTransaction fragTRJadwal, fragTRMK, fragTRMe;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navView = findViewById(R.id.navBar);

        BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragmentJadwal = null;
                fragmentMK = null;
                fragmentMe = null;
                fragTRJadwal = null;
                fragTRMK = null;
                fragTRMe = null;

                switch (item.getItemId()) {
                    case R.id.jadwal_hari_ini:
                        readData(new callbackFB() {
                            @Override
                            public void onCallback(List<String> listId, List<String> listNama, List<String> listHari, List<String> listKelas, List<String> listWaktu) {
                                FragmentJadwal fragmentJadwal = new FragmentJadwal();
                                fragmentJadwal.listNama.clear();
                                fragmentJadwal.listWaktu.clear();
                                fragmentJadwal.listKelas.clear();
                                fragmentJadwal.listHari.clear();

                                String day = getDate();
                                Log.d("Hari ini", "Hari : "+day);
                                for(int i = 0; i < listHari.size(); i++) {
                                    if(listHari.get(i).equals(day) ) {
                                        fragmentJadwal.listNama.add(listNama.get(i));
                                        fragmentJadwal.listHari.add(listHari.get(i));
                                        fragmentJadwal.listKelas.add(listKelas.get(i));
                                        fragmentJadwal.listWaktu.add(listWaktu.get(i));
                                    }
                                }

                                Date date = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
                                String strDate = format.format(date);
                                fragmentJadwal.tanggal = day + ", " + strDate;

                                fragTRJadwal = getSupportFragmentManager().beginTransaction();
                                fragTRJadwal.replace(R.id.display, fragmentJadwal);
                                fragTRJadwal.commit();

                            }
                        });
                        fragmentJadwal = new FragmentJadwal();
                        getSupportFragmentManager().beginTransaction().replace(R.id.display, fragmentJadwal, fragmentJadwal.getClass().getSimpleName()).commit();
                        return true;

                    case R.id.mata_kuliah:
                        readData(new callbackFB() {
                            @Override
                            public void onCallback(List<String> listId, List<String> listNama, List<String> listHari, List<String> listKelas, List<String> listWaktu) {
                                FragmentMK fragmentMK = new FragmentMK();
                                fragmentMK.listNama.clear();
                                fragmentMK.listWaktu.clear();
                                fragmentMK.listKelas.clear();
                                fragmentMK.listHari.clear();

                                fragmentMK.listNama.addAll(listNama);
                                fragmentMK.listHari.addAll(listHari);
                                fragmentMK.listKelas.addAll(listKelas);
                                fragmentMK.listWaktu.addAll(listWaktu);

                                fragTRMK = getSupportFragmentManager().beginTransaction();
                                fragTRMK.replace(R.id.display, fragmentMK);
                                fragTRMK.commit();
                            }
                        });
                        return true;
                    case R.id.about_me:
                        FragmentAboutMe fragmentMe = new FragmentAboutMe();
                        fragTRMe = getSupportFragmentManager().beginTransaction();
                        fragTRMe.replace(R.id.display, fragmentMe, fragmentMe.getClass().getSimpleName()).commit();
                        return true;
                }
                return true;
            }
        };

        navView = findViewById(R.id.navBar);
        navView.setOnNavigationItemSelectedListener(listener);
        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.jadwal_hari_ini);
        }


    }

    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private interface callbackFB {
        void onCallback(List<String> listId, List<String> listNama, List<String> listHari, List<String> listKelas, List<String> listWaktu);
    }

    private void readData(callbackFB callbackFB) {
        ArrayList<String> dbListId = new ArrayList<>();
        ArrayList<String> dbListNama = new ArrayList<>();
        ArrayList<String> dbListWaktu = new ArrayList<>();
        ArrayList<String> dbListHari = new ArrayList<>();
        ArrayList<String> dbListKelas = new ArrayList<>();

        db.collection("jadwal").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Log.d("RES", "Success: "+ documentSnapshot.getData());
                        dbListId.add(documentSnapshot.getId());
                        dbListNama.add(documentSnapshot.get("namaMK").toString());
                        dbListHari.add(documentSnapshot.get("hariMK").toString());
                        dbListKelas.add(documentSnapshot.get("kelasMK").toString());
                        dbListWaktu.add(documentSnapshot.get("waktuMK").toString());
                    }
                    Log.d("RES", "Complete : " + dbListId);
                    callbackFB.onCallback(dbListId, dbListNama, dbListHari, dbListKelas, dbListWaktu);
                } else {
                    Log.w("RES", "onComplete failed", task.getException());
                }
            }
        });
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String rt = "week";
        switch (day) {
            case Calendar.SUNDAY:
                rt = "Minggu";
                break;
            case Calendar.MONDAY:
                rt = "Senin";
                break;
            case Calendar.TUESDAY:
                rt = "Selasa";
                break;
            case Calendar.WEDNESDAY:
                rt = "Rabu";
                break;
            case Calendar.THURSDAY:
                rt = "Kamis";
                break;
            case Calendar.FRIDAY:
                rt = "Jumat";
                break;
            case Calendar.SATURDAY:
                rt = "Sabtu";
                break;
        }
        return rt;
    }

}