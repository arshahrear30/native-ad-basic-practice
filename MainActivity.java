package com.example.animsplash;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdViewHolder;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;

//https://github.com/arshahrear30/RecyclerViewBasic2.0/blob/main/MainActivity.java
//এই কোডের comment পড়া উচিত আগে
//290
//https://developers.google.com/admob/android/native/templates
//layout a item item_native_ad.xml create kori
//Manifest a internet dai


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        //1.Mobile ad initalize korlam
        //https://developers.google.com/admob/android/quick-start#initialize_the_mobile_ads_sdk
        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        arrayList = new ArrayList<>();

        hashMap = new HashMap<>();
        hashMap.put("itemType", "BOOK");
        hashMap.put("bookName", "কফয়জুল (হার্ডকভার)");
        hashMap.put("writerName", "মুফতীয়ে আযম");
        hashMap.put("bookLink", "https://www.rokomari.com/book/325210/foyjul-kalam");
        hashMap.put("bookImage", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Foyjul_Kalam-Mufti_Azam_Faizullah_Rah-77538-325210.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "VIDEO");
        hashMap.put("videoTitle", "The Coldest Village on Earth (10 minutes outside = Death) ");
        hashMap.put("videoId", "2C8zYFArnKY");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "BOOK");
        hashMap.put("bookName", "কোথাও কেউ নেই");
        hashMap.put("writerName", "হুমায়ূন আহমেদ");
        hashMap.put("bookLink", "https://www.rokomari.com/book/1241/kothao-kew-nei");
        hashMap.put("bookImage", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Kothao_Kew_Nei-Humayun_Ahmed-f86bd-1241.jpg");
        arrayList.add(hashMap);


        //2.NATIVE_AD itemType hashMap create
        hashMap = new HashMap<>();
        hashMap.put("itemType", "NATIVE_AD");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "BOOK");
        hashMap.put("bookName", "কফয়জুল (হার্ডকভার)");
        hashMap.put("writerName", "মুফতীয়ে আযম");
        hashMap.put("bookLink", "https://www.rokomari.com/book/325210/foyjul-kalam");
        hashMap.put("bookImage", "https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/Foyjul_Kalam-Mufti_Azam_Faizullah_Rah-77538-325210.jpg");
        arrayList.add(hashMap);


        XAdapter adapter = new XAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    private class XAdapter extends RecyclerView.Adapter{

        int BOOK_ITEM =0;
        int VIDEO_ITEM =1;
        int NATIVE_AD_ITEM =2;


        private class videoViewHolder extends RecyclerView.ViewHolder {

            TextView tvVideoTitle;
            ImageView imgThumbnail;
            public videoViewHolder(@NonNull View itemView) {
                super(itemView);

                tvVideoTitle = itemView.findViewById(R.id.tvVideoTitle);
                imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            }
        }

        private class bookViewHolder extends RecyclerView.ViewHolder {

            ImageView imgBook;
            TextView tvBookName, tvWriterName;
            MaterialButton buttonGetBook;

            public bookViewHolder(@NonNull View itemView) {
                super(itemView);

                imgBook = itemView.findViewById(R.id.imgBook);
                tvBookName = itemView.findViewById(R.id.tvBookName);
                tvWriterName = itemView.findViewById(R.id.tvWriterName);
                buttonGetBook = itemView.findViewById(R.id.buttonGetBook);

            }
        }

        //3. nativeAdViewHolder create
        private class nativeAdViewHolder extends RecyclerView.ViewHolder {
            TemplateView templateView; //6.

            public nativeAdViewHolder(@NonNull View itemView) {
                super(itemView);
                templateView=itemView.findViewById(R.id.templateView);//7.
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();


            if (viewType == BOOK_ITEM) {
                View myView = inflater.inflate(R.layout.item_book, parent, false);
                return new bookViewHolder(myView);
            }

            else if (viewType == NATIVE_AD_ITEM) { //5. else if  NATIVE_AD_ITEM create
                View myView = inflater.inflate(R.layout.item_native_ad, parent, false);
                return new nativeAdViewHolder(myView);

            }

            else {
                View myView = inflater.inflate(R.layout.item_video, parent, false);
                return new videoViewHolder(myView);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (getItemViewType(position) == BOOK_ITEM) {
                bookViewHolder myHolder = (bookViewHolder) holder;

                hashMap = arrayList.get(position);
                String bookName = hashMap.get("bookName");
                String writerName = hashMap.get("writerName");
                String bookLink = hashMap.get("bookLink");
                String bookImage = hashMap.get("bookImage");

                myHolder.tvBookName.setText(bookName);
                myHolder.tvWriterName.setText(writerName);

                myHolder.buttonGetBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), bookLink, Toast.LENGTH_SHORT).show();
                    }
                });

                Glide.with(MainActivity.this)
                .load(bookImage)
                .into(myHolder.imgBook);

            }

            else if (getItemViewType(position)==VIDEO_ITEM) {

                videoViewHolder myHolder = (videoViewHolder) holder;

                hashMap = arrayList.get(position);
                String videoTitle = hashMap.get("videoTitle");
                String videoId = hashMap.get("videoId");
                String imgUrl = "https://img.youtube.com/vi/" + videoId + "/0.jpg";

                myHolder.tvVideoTitle.setText(videoTitle);

                Glide.with(MainActivity.this)
                        .load(imgUrl)
                        .into(myHolder.imgThumbnail);
            }

            else if (getItemViewType(position)==NATIVE_AD_ITEM) { //8.
                nativeAdViewHolder myHolder = (nativeAdViewHolder) holder;

                AdLoader adLoader = new AdLoader.Builder(MainActivity.this, "ca-app-pub-3940256099942544/2247696110")
                        .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(NativeAd nativeAd) {



                                NativeTemplateStyle styles = new NativeTemplateStyle.Builder()
                                        .withMainBackgroundColor(new ColorDrawable(Color.WHITE))
                                        .build();
                            }
                        })
                        .build();

                adLoader.loadAd(new AdRequest.Builder().build());


            }

        }
        @Override
        public int getItemCount() {
            return arrayList.size();
            }
        @Override
        public int getItemViewType(int position) {
            hashMap = arrayList.get(position);
            String itemType = hashMap.get("itemType");
                    if (itemType.contains("BOOK")) return BOOK_ITEM;
                    else if (itemType.contains("NATIVE_AD"))return NATIVE_AD_ITEM; //4.NATIVE_AD ad korlam
                    else return VIDEO_ITEM;
        }
    }



}
