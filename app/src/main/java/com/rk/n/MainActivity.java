package com.rk.n;
/*
    Created by Rizky Khapidsyah
 */
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView TV_Bahasa_IDJAVA, TV_JudulUtama_IDJAVA;
    Spinner S_Bahasa_IDJAVA;
    Button B_Notification_Simple_IDJAVA, B_Notification_BigTextStyle_IDJAVA,
            B_Notification_BigPictureStyle_IDJAVA, B_Notification_InboxStyle_IDJAVA, B_Keluar_IDJAVA;

    String[] ListBahasa = {
            "Indonesia",
            "Inggris"
    };

    String[] ListCaption = {
            "Pilih Bahasa : ",
            "Klik Untuk Melihat Contoh Notifikasi :",
            "Notifikasi: Simple",
            "Notifikasi: BigTextStyle",
            "Notifikasi: BigPictureStyle",
            "Notifikasi: InboxStyle",
            "Keluar",

            "Choose Language : ",
            "Click To See The Notification Example :",
            "Notification: Simple Model",
            "Notification: BigTextStyle Model",
            "Notification: BigPictureStyle Model",
            "Notification: InboxStyle Model",
            "Exit"
    };

    AlertDialog.Builder PembentukDialog;
    AlertDialog PenampilDialog;

    String ID_CHANNEL;
    int ID_NOTIFICATION;
    NotificationCompat.Builder PembentukNotifikasi;
    NotificationManagerCompat PengaturNotifikasi;
    Bitmap Gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV_Bahasa_IDJAVA = findViewById(R.id.TV_Bahasa_IDXML);
        TV_JudulUtama_IDJAVA = findViewById(R.id.TV_JudulUtama_IDXML);
        S_Bahasa_IDJAVA = findViewById(R.id.S_Bahasa_IDXML);
        B_Notification_Simple_IDJAVA = findViewById(R.id.B_Notification_Simple_IDXML);
        B_Notification_BigTextStyle_IDJAVA = findViewById(R.id.B_Notification_BigTextStyle_IDXML);
        B_Notification_BigPictureStyle_IDJAVA = findViewById(R.id.B_Notification_BigPictureStyle_IDXML);
        B_Notification_InboxStyle_IDJAVA = findViewById(R.id.B_Notification_InboxStyle_IDXML);
        B_Keluar_IDJAVA = findViewById(R.id.B_Keluar_IDXML);

        ArrayAdapter AdapterSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ListBahasa);
        AdapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        S_Bahasa_IDJAVA.setAdapter(AdapterSpinner);
        S_Bahasa_IDJAVA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (S_Bahasa_IDJAVA.getSelectedItemPosition()) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Anda Memilih Bahasa Indonesia", Toast.LENGTH_LONG).show();
                        TV_Bahasa_IDJAVA.setText(ListCaption[0]);
                        TV_JudulUtama_IDJAVA.setText(ListCaption[1]);
                        B_Notification_Simple_IDJAVA.setText(ListCaption[2]);
                        B_Notification_BigTextStyle_IDJAVA.setText(ListCaption[3]);
                        B_Notification_BigPictureStyle_IDJAVA.setText(ListCaption[4]);
                        B_Notification_InboxStyle_IDJAVA.setText(ListCaption[5]);
                        B_Keluar_IDJAVA.setText(ListCaption[6]);
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "You Choose English", Toast.LENGTH_LONG).show();
                        TV_Bahasa_IDJAVA.setText(ListCaption[7]);
                        TV_JudulUtama_IDJAVA.setText(ListCaption[8]);
                        B_Notification_Simple_IDJAVA.setText(ListCaption[9]);
                        B_Notification_BigTextStyle_IDJAVA.setText(ListCaption[10]);
                        B_Notification_BigPictureStyle_IDJAVA.setText(ListCaption[11]);
                        B_Notification_InboxStyle_IDJAVA.setText(ListCaption[12]);
                        B_Keluar_IDJAVA.setText(ListCaption[13]);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Tidak Melakukan Apapun
            }
        });

        B_Notification_Simple_IDJAVA.setOnClickListener(this);
        B_Notification_BigTextStyle_IDJAVA.setOnClickListener(this);
        B_Notification_BigPictureStyle_IDJAVA.setOnClickListener(this);
        B_Notification_InboxStyle_IDJAVA.setOnClickListener(this);
        B_Keluar_IDJAVA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.B_Notification_Simple_IDXML) {
            ID_CHANNEL = "simple_notification";
            ID_NOTIFICATION = 01;

            //Membuat Channel Notification Jika Target Versi Android 8.0 atau diatasnya
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence NamaNotifikasi = "Simple Notification";
                String DeskripsiNotifikasi = "Include All the Simple Notification";
                int Kepentingan = NotificationManager.IMPORTANCE_DEFAULT;

                NotificationChannel ChannelNotifikasi = new NotificationChannel(ID_CHANNEL, NamaNotifikasi, Kepentingan);
                ChannelNotifikasi.setDescription(DeskripsiNotifikasi);
                NotificationManager PengaturNotifikasi = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PengaturNotifikasi.createNotificationChannel(ChannelNotifikasi);
            }

            PembentukNotifikasi = new NotificationCompat.Builder(getApplicationContext(), ID_CHANNEL);
            PembentukNotifikasi.setSmallIcon(R.mipmap.ic_launcher);
            PembentukNotifikasi.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
            PembentukNotifikasi.setContentTitle("Belajar Dasar Android by Rizky Khapidsyah");
            PembentukNotifikasi.setContentText("Ini adalah Simple Notification");
            PembentukNotifikasi.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            PengaturNotifikasi = NotificationManagerCompat.from(getApplicationContext());
            PengaturNotifikasi.notify(ID_NOTIFICATION, PembentukNotifikasi.build());
        } else if (v.getId() == R.id.B_Notification_BigTextStyle_IDXML) {
            ID_CHANNEL = "big_text_style_notification";
            ID_NOTIFICATION = 02;

            //Membuat Channel Notification Jika Target Versi Android 8.0 atau diatasnya
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence NamaNotifikasi = "BigTextStyle Notification";
                String DeskripsiNotifikasi = "Include All the BIgTextStyle Notification";
                int Kepentingan = NotificationManager.IMPORTANCE_DEFAULT;

                NotificationChannel ChannelNotifikasi = new NotificationChannel(ID_CHANNEL, NamaNotifikasi, Kepentingan);
                ChannelNotifikasi.setDescription(DeskripsiNotifikasi);
                NotificationManager PengaturNotifikasi = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PengaturNotifikasi.createNotificationChannel(ChannelNotifikasi);
            }

            PembentukNotifikasi = new NotificationCompat.Builder(getApplicationContext(), ID_CHANNEL);
            PembentukNotifikasi.setSmallIcon(R.mipmap.ic_launcher);
            PembentukNotifikasi.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
            PembentukNotifikasi.setContentTitle("Rizky Khapidsyah");
            PembentukNotifikasi.setStyle(new NotificationCompat.BigTextStyle().bigText("Ini merupakan contoh dari BigTextStyle Notifikasi yang telah Anda buat"));
            PembentukNotifikasi.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            PengaturNotifikasi = NotificationManagerCompat.from(getApplicationContext());
            PengaturNotifikasi.notify(ID_NOTIFICATION, PembentukNotifikasi.build());
        } else if (v.getId() == R.id.B_Notification_BigPictureStyle_IDXML) {
            ID_CHANNEL = "big_picture_style_notification";
            ID_NOTIFICATION = 03;

            //Membuat Channel Notification Jika Target Versi Android 8.0 atau diatasnya
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence NamaNotifikasi = "BigPictureStyle Notification";
                String DeskripsiNotifikasi = "Include All the BigPictureStyle Notification";
                int Kepentingan = NotificationManager.IMPORTANCE_DEFAULT;

                NotificationChannel ChannelNotifikasi = new NotificationChannel(ID_CHANNEL, NamaNotifikasi, Kepentingan);
                ChannelNotifikasi.setDescription(DeskripsiNotifikasi);
                NotificationManager PengaturNotifikasi = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PengaturNotifikasi.createNotificationChannel(ChannelNotifikasi);
            }

            Gambar = BitmapFactory.decodeResource(getResources(), R.drawable.contoh_gambar);
            PembentukNotifikasi = new NotificationCompat.Builder(getApplicationContext(), ID_CHANNEL);
            PembentukNotifikasi.setSmallIcon(R.drawable.contoh_gambar2);
            PembentukNotifikasi.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.contoh_gambar));
            PembentukNotifikasi.setContentTitle("Pemberitahuan!");
            PembentukNotifikasi.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(Gambar));
            PembentukNotifikasi.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            PengaturNotifikasi = NotificationManagerCompat.from(getApplicationContext());
            PengaturNotifikasi.notify(ID_NOTIFICATION, PembentukNotifikasi.build());
        } else if (v.getId() == R.id.B_Notification_InboxStyle_IDXML) {
            ID_CHANNEL = "inbox_style_notification";
            ID_NOTIFICATION = 04;

            //Membuat Channel Notification Jika Target Versi Android 8.0 atau diatasnya
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence NamaNotifikasi = "BigPictureStyle Notification";
                String DeskripsiNotifikasi = "Include All the BigPictureStyle Notification";
                int Kepentingan = NotificationManager.IMPORTANCE_DEFAULT;

                NotificationChannel ChannelNotifikasi = new NotificationChannel(ID_CHANNEL, NamaNotifikasi, Kepentingan);
                ChannelNotifikasi.setDescription(DeskripsiNotifikasi);
                NotificationManager PengaturNotifikasi = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PengaturNotifikasi.createNotificationChannel(ChannelNotifikasi);
            }

            PembentukNotifikasi = new NotificationCompat.Builder(getApplicationContext(), ID_CHANNEL);
            PembentukNotifikasi.setSmallIcon(R.drawable.contoh_gambar2);
            PembentukNotifikasi.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.contoh_gambar));
            PembentukNotifikasi.setContentTitle("7 Pesan Baru!");
            PembentukNotifikasi.setStyle(new NotificationCompat.InboxStyle()
                    .addLine("Hai Kamu, Apakabar?")
                    .addLine("Ini saya, Rizky Khapidsyah!")
                    .addLine("Kamu Sedang dimana?")
                    .addLine("Lagi Ngapain?")
                    .setSummaryText("+3 Pesan Lainnya"));
            PembentukNotifikasi.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            PengaturNotifikasi = NotificationManagerCompat.from(getApplicationContext());
            PengaturNotifikasi.notify(ID_NOTIFICATION, PembentukNotifikasi.build());
        } else if (v.getId() == R.id.B_Keluar_IDXML) {
            PembentukDialog = new AlertDialog.Builder(MainActivity.this);

            PembentukDialog
                    .setMessage("Keluar Aplikasi?")
                    .setIcon(R.drawable.contoh_gambar2)
                    .setTitle("Keluar")
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Tidak Jadi Keluar", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });

            PenampilDialog = PembentukDialog.create();
            PenampilDialog.show();
        }

    }

}
