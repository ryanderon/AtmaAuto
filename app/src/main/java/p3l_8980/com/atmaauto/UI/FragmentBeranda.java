package p3l_8980.com.atmaauto.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import p3l_8980.com.atmaauto.R;


public class FragmentBeranda extends Fragment {

    private TextView tJadwal, tTugas, tPlus, tMinus;
    private TextView spWaktu1, spNama1,
            spWaktu2, spNama2,
            spWaktu3, spNama3;
    private TextView tSisa, NamaUser;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_beranda,container,false);


        return view;
    }

}
