package com.mediasoftindonesia.taniapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.mediasoftindonesia.taniapps.api.Artikel;
import com.mediasoftindonesia.taniapps.api.ArtikelService;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends android.support.v4.app.Fragment implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener{

    SliderLayout sliderLayout;
    ListView list_Artikel;
    HashMap<String, Integer> HashMapForLocalRes;

    private Artikel kumpArtikel[];
    String url = "http://tukangonline.hol.es/server/index.php/";
    Context context;
    private SharedPreferences sp;
    private List<Artikel> verif;
    private ArtikelService ser;
    private String id_artikel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        sp=getActivity().getSharedPreferences("artikel", Context.MODE_PRIVATE);
        String id=sp.getString("id_artikel",null);

        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);

        AddImageUrlFormLocalRes();

        for (String named : HashMapForLocalRes.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
            textSliderView
                    .description(named)
                    .image(HashMapForLocalRes.get(named))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",named);

            sliderLayout.addSlider(textSliderView);

        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(5000);
        sliderLayout.addOnPageChangeListener(HomeFragment.this);

        //GET DATA
        sp=getActivity().getSharedPreferences("artikel", MODE_PRIVATE);
        id = sp.getString("id_artikel", null);
        list_Artikel = (ListView) view.findViewById(R.id.listArtikel);

        //final String judul = this.verif.get(id_artikel).getJudul();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ser = retrofit.create(ArtikelService.class);

        ser.getArtikel(id_artikel).enqueue(new Callback<List<Artikel>>() {
            @Override
            public void onResponse(Call<List<Artikel>> call, Response<List<Artikel>> response) {
                final List<Artikel> data = response.body();

                verif = response.body();

                String judul1 = "";
                String tgl1 = "";
                String deskripsi1 = "";
                String foto1 = "";

                for (Artikel m : verif){
                    judul1 = m.getJudul();
                    tgl1 = m.getTanggal();
                    deskripsi1 = m.getDeskripsi();
                    foto1 = m.getFoto();
                }

                final CustomListViewBerandaPetani adapter= new CustomListViewBerandaPetani(getContext(),data);
                list_Artikel.setAdapter(adapter);
                registerForContextMenu(list_Artikel);
            }

            @Override
            public void onFailure(Call<List<Artikel>> call, Throwable throwable) {

            }
        });

        return view;
    }

    @Override
    public void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImageUrlFormLocalRes(){

        HashMapForLocalRes = new HashMap<String, Integer>();

        HashMapForLocalRes.put("cek1", R.drawable.banner1);
        HashMapForLocalRes.put("cek2", R.drawable.banner2);
        HashMapForLocalRes.put("cek3", R.drawable.banner3);

    }
}
