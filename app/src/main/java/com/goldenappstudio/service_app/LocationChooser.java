package com.goldenappstudio.service_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LocationChooser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner stateSpinner, distSpinner;
    public static String PROVINCE, COUNTY;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_chooser);

        getSupportActionBar().setTitle("Location Chooser");

        stateSpinner = findViewById(R.id.state_spinnerZ);
        distSpinner = findViewById(R.id.district_chooserZ);
        button = findViewById(R.id.dac);
        stateSpinner.setOnItemSelectedListener(this);

        button.setOnClickListener(view -> {
            PROVINCE = stateSpinner.getSelectedItem().toString();
            COUNTY = distSpinner.getSelectedItem().toString();
            Intent intent = new Intent(LocationChooser.this, ServiceProviderList.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sp1= String.valueOf(stateSpinner.getSelectedItem());
        if(sp1.contentEquals("Andhra Pradesh")) {
            List<String> list = new ArrayList<String>();
            list.add("Anantapur");
            list.add("Chittoor");
            list.add("East Godavari");
            list.add("Guntur");
            list.add("Krishna");
            list.add("Kurnool");
            list.add("Nellore");
            list.add("Prakasam");
            list.add("Srikakulam");
            list.add("Visakhapatnam");
            list.add("Vizianagaram");
            list.add("West Godavari");
            list.add("YSR Kadapa");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Haryana")) {
            List<String> list = new ArrayList<String>();
            list.add("Ambala");
            list.add("Bhiwani");
            list.add("Charkhi Dadri");
            list.add("Faridabad");
            list.add("Fatehabad");
            list.add("Gurgaon");
            list.add("Hisar");
            list.add("Jhajjar");
            list.add("Jind");
            list.add("Kaithal");
            list.add("Karnal");
            list.add("Kurukshetra");
            list.add("Mahendragarh");
            list.add("Mewat");
            list.add("Palwal");
            list.add("Panchkula");
            list.add("Panipat");
            list.add("Rewari");
            list.add("Rohtak");
            list.add("Sirsa");
            list.add("Sonipat");
            list.add("Yamunanagar");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Arunachal Pradesh")) {
            List<String> list = new ArrayList<String>();
            list.add("Tawang");
            list.add("West Kameng");
            list.add("East Kameng");
            list.add("Papum Pare");
            list.add("Kurung Kumey");
            list.add("Kra Daadi");
            list.add("Lower Subansiri");
            list.add("Upper Subansiri");
            list.add("West Siang");
            list.add("East Siang");
            list.add("Siang");
            list.add("Upper Siang");
            list.add("Lower Siang");
            list.add("Lower Dibang Valley");
            list.add("Dibang Valley");
            list.add("Anjaw");
            list.add("Lohit");
            list.add("Namsai");
            list.add("Changlang");
            list.add("Tirap");
            list.add("Longding");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Assam")) {
            List<String> list = new ArrayList<String>();
            list.add("Baksa");
            list.add("Barpeta");
            list.add("Biswanath");
            list.add("Bongaigaon");
            list.add("Cachar");
            list.add("Charaideo");
            list.add("Chirang");
            list.add("Darrang");
            list.add("Dhemaji");
            list.add("Dhubri");
            list.add("Dibrugarh");
            list.add("Goalpara");
            list.add("Golaghat");
            list.add("Hailakandi");
            list.add("Hojai");
            list.add("Jorhat");
            list.add("Kamrup Metropolitan");
            list.add("Kamrup");
            list.add("Karbi Anglong");
            list.add("Karimganj");
            list.add("Kokrajhar");
            list.add("Lakhimpur");
            list.add("Majuli");
            list.add("Morigaon");
            list.add("Nagaon");
            list.add("Nalbari");
            list.add("Dima Hasao");
            list.add("Sivasagar");
            list.add("Sonitpur");
            list.add("South Salmara-Mankachar");
            list.add("Tinsukia");
            list.add("Udalguri");
            list.add("West Karbi Anglong");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Bihar")) {
            List<String> list = new ArrayList<String>();
            list.add("Araria");
            list.add("Arwal");
            list.add("Aurangabad");
            list.add("Banka");
            list.add("Begusarai");
            list.add("Bhagalpur");
            list.add("Bhojpur");
            list.add("Buxar");
            list.add("Darbhanga");
            list.add("East Champaran (Motihari)");
            list.add("Gaya");
            list.add("Gopalganj");
            list.add("Jamui");
            list.add("Jehanabad");
            list.add("Kaimur (Bhabua)");
            list.add("Katihar");
            list.add("Khagaria");
            list.add("Kishanganj");
            list.add("Lakhisarai");
            list.add("Madhepura");
            list.add("Madhubani");
            list.add("Munger (Monghyr)");
            list.add("Muzaffarpur");
            list.add("Nalanda");
            list.add("Nawada");
            list.add("Patna");
            list.add("Purnia (Purnea)");
            list.add("Rohtas");
            list.add("Saharsa");
            list.add("Samastipur");
            list.add("Saran");
            list.add("Sheikhpura");
            list.add("Sheohar");
            list.add("Sitamarhi");
            list.add("Siwan");
            list.add("Supaul");
            list.add("Vaishali");
            list.add("West Champaran");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Chandigarh (UT)")) {
            List<String> list = new ArrayList<String>();
            list.add("Chandigarh");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Chhattisgarh")) {
            List<String> list = new ArrayList<String>();
            list.add("Balod");
            list.add("Baloda Bazar");
            list.add("Balrampur");
            list.add("Bastar");
            list.add("Bemetara");
            list.add("Bijapur");
            list.add("Bilaspur");
            list.add("Dantewada (South Bastar)");
            list.add("Dhamtari");
            list.add("Durg");
            list.add("Gariyaband");
            list.add("Janjgir-Champa");
            list.add("Jashpur");
            list.add("Kabirdham (Kawardha)");
            list.add("Kanker (North Bastar)");
            list.add("Kondagaon");
            list.add("Korba");
            list.add("Korea (Koriya)");
            list.add("Mahasamund");
            list.add("Mungeli");
            list.add("Narayanpur");
            list.add("Raigarh");
            list.add("Raipur");
            list.add("Rajnandgaon");
            list.add("Sukma");
            list.add("Surajpur");
            list.add("Surguja");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Dadra & Nagar Haveli (UT)")) {
            List<String> list = new ArrayList<String>();
            list.add("Dadra & Nagar Haveli");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Daman and Diu (UT)")) {
            List<String> list = new ArrayList<String>();
            list.add("Daman");
            list.add("Diu");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Delhi (NCT)")) {
            List<String> list = new ArrayList<String>();
            list.add("Central Delhi");
            list.add("East Delhi");
            list.add("New Delhi");
            list.add("North Delhi");
            list.add("North East Delhi");
            list.add("North West Delhi");
            list.add("Shahdara");
            list.add("South Delhi");
            list.add("South East Delhi");
            list.add("South West Delhi");
            list.add("West Delhi");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Goa")) {
            List<String> list = new ArrayList<String>();
            list.add("North Goa");
            list.add("South Goa");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Gujarat")) {
            List<String> list = new ArrayList<String>();
            list.add("Ahmedabad");
            list.add("Amreli");
            list.add("Anand");
            list.add("Aravalli");
            list.add("Banaskantha (Palanpur)");
            list.add("Bharuch");
            list.add("Bhavnagar");
            list.add("Botad");
            list.add("Chhota Udepur");
            list.add("Dahod");
            list.add("Dangs (Ahwa)");
            list.add("Devbhoomi Dwarka");
            list.add("Gandhinagar");
            list.add("Gir Somnath");
            list.add("Jamnagar");
            list.add("Junagadh");
            list.add("Kachchh");
            list.add("Kheda (Nadiad)");
            list.add("Mahisagar");
            list.add("Mehsana");
            list.add("Morbi");
            list.add("Narmada (Rajpipla)");
            list.add("Navsari");
            list.add("Panchmahal (Godhra)");
            list.add("Patan");
            list.add("Porbandar");
            list.add("Rajkot");
            list.add("Sabarkantha (Himmatnagar)");
            list.add("Surat");
            list.add("Surendranagar");
            list.add("Tapi (Vyara)");
            list.add("Vadodara");
            list.add("Valsad");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Himachal Pradesh")) {
            List<String> list = new ArrayList<String>();
            list.add("Bilaspur");
            list.add("Chamba");
            list.add("Hamirpur");
            list.add("Kangra");
            list.add("Kinnaur");
            list.add("Kullu");
            list.add("Lahaul &amp; Spiti");
            list.add("Mandi");
            list.add("Shimla");
            list.add("Sirmaur (Sirmour)");
            list.add("Solan");
            list.add("Una");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Jammu and Kashmir")) {
            List<String> list = new ArrayList<String>();
            list.add("Anantnag");
            list.add("Bandipore");
            list.add("Baramulla");
            list.add("Budgam");
            list.add("Doda");
            list.add("Ganderbal");
            list.add("Jammu");
            list.add("Kargil");
            list.add("Kathua");
            list.add("Kishtwar");
            list.add("Kulgam");
            list.add("Kupwara");
            list.add("Leh");
            list.add("Poonch");
            list.add("Pulwama");
            list.add("Rajouri");
            list.add("Ramban");
            list.add("Reasi");
            list.add("Samba");
            list.add("Shopian");
            list.add("Srinagar");
            list.add("Udhampur");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Jharkhand")) {
            List<String> list = new ArrayList<String>();
            list.add("Bokaro");
            list.add("Chatra");
            list.add("Deoghar");
            list.add("Dhanbad");
            list.add("Dumka");
            list.add("East Singhbhum");
            list.add("Garhwa");
            list.add("Giridih");
            list.add("Godda");
            list.add("Gumla");
            list.add("Hazaribag");
            list.add("Jamtara");
            list.add("Khunti");
            list.add("Koderma");
            list.add("Latehar");
            list.add("Lohardaga");
            list.add("Pakur");
            list.add("Palamu");
            list.add("Ramgarh");
            list.add("Ranchi");
            list.add("Sahibganj");
            list.add("Seraikela-Kharsawan");
            list.add("Simdega");
            list.add("West Singhbhum");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Karnataka")) {
            List<String> list = new ArrayList<String>();
            list.add("Bagalkot");
            list.add("Ballari (Bellary)");
            list.add("Belagavi (Belgaum)");
            list.add("Bengaluru (Bangalore) Rural");
            list.add("Bengaluru (Bangalore) Urban");
            list.add("Bidar");
            list.add("Chamarajanagar");
            list.add("Chikballapur");
            list.add("Chikkamagaluru (Chikmagalur)");
            list.add("Chitradurga");
            list.add("Dakshina Kannada");
            list.add("Davangere");
            list.add("Dharwad");
            list.add("Gadag");
            list.add("Hassan");
            list.add("Haveri");
            list.add("Kalaburagi (Gulbarga)");
            list.add("Kodagu");
            list.add("Kolar");
            list.add("Koppal");
            list.add("Mandya");
            list.add("Mysuru (Mysore)");
            list.add("Raichur");
            list.add("Ramanagara");
            list.add("Shivamogga (Shimoga)");
            list.add("Tumakuru (Tumkur)");
            list.add("Udupi");
            list.add("Uttara Kannada (Karwar)");
            list.add("Vijayapura (Bijapur)");
            list.add("Yadgir");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Kerala")) {
            List<String> list = new ArrayList<String>();
            list.add("Alappuzha");
            list.add("Ernakulam");
            list.add("Idukki");
            list.add("Kannur");
            list.add("Kasaragod");
            list.add("Kollam");
            list.add("Kottayam");
            list.add("Kozhikode");
            list.add("Malappuram");
            list.add("Palakkad");
            list.add("Pathanamthitta");
            list.add("Thiruvananthapuram");
            list.add("Thrissur");
            list.add("Wayanad");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Lakshadweep (UT)")) {
            List<String> list = new ArrayList<String>();
            list.add("Agatti");
            list.add("Amini");
            list.add("Androth");
            list.add("Bithra");
            list.add("Chethlath");
            list.add("Kavaratti");
            list.add("Kadmath");
            list.add("Kalpeni");
            list.add("Kilthan");
            list.add("Minicoy");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Madhya Pradesh")) {
            List<String> list = new ArrayList<String>();
            list.add("Agar Malwa");
            list.add("Alirajpur");
            list.add("Anuppur");
            list.add("Ashoknagar");
            list.add("Balaghat");
            list.add("Barwani");
            list.add("Betul");
            list.add("Bhind");
            list.add("Bhopal");
            list.add("Burhanpur");
            list.add("Chhatarpur");
            list.add("Chhindwara");
            list.add("Damoh");
            list.add("Datia");
            list.add("Dewas");
            list.add("Dhar");
            list.add("Dindori");
            list.add("Guna");
            list.add("Gwalior");
            list.add("Harda");
            list.add("Hoshangabad");
            list.add("Indore");
            list.add("Jabalpur");
            list.add("Jhabua");
            list.add("Katni");
            list.add("Khandwa");
            list.add("Khargone");
            list.add("Mandla");
            list.add("Mandsaur");
            list.add("Morena");
            list.add("Narsinghpur");
            list.add("Neemuch");
            list.add("Panna");
            list.add("Raisen");
            list.add("Rajgarh");
            list.add("Ratlam");
            list.add("Rewa");
            list.add("Sagar");
            list.add("Satna");
            list.add("Sehore");
            list.add("Seoni");
            list.add("Shahdol");
            list.add("Shajapur");
            list.add("Sheopur");
            list.add("Shivpuri");
            list.add("Sidhi");
            list.add("Singrauli");
            list.add("Tikamgarh");
            list.add("Ujjain");
            list.add("Umaria");
            list.add("Vidisha");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Maharashtra")) {
            List<String> list = new ArrayList<String>();
            list.add("Ahmednagar");
            list.add("Akola");
            list.add("Amravati");
            list.add("Aurangabad");
            list.add("Beed");
            list.add("Bhandara");
            list.add("Buldhana");
            list.add("Chandrapur");
            list.add("Dhule");
            list.add("Gadchiroli");
            list.add("Gondia");
            list.add("Hingoli");
            list.add("Jalgaon");
            list.add("Jalna");
            list.add("Kolhapur");
            list.add("Latur");
            list.add("Mumbai City");
            list.add("Mumbai Suburban");
            list.add("Nagpur");
            list.add("Nanded");
            list.add("Nandurbar");
            list.add("Nashik");
            list.add("Osmanabad");
            list.add("Palghar");
            list.add("Parbhani");
            list.add("Pune");
            list.add("Raigad");
            list.add("Ratnagiri");
            list.add("Sangli");
            list.add("Satara");
            list.add("Sindhudurg");
            list.add("Solapur");
            list.add("Thane");
            list.add("Wardha");
            list.add("Washim");
            list.add("Yavatmal");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Manipur")) {
            List<String> list = new ArrayList<String>();
            list.add("Bishnupur");
            list.add("Chandel");
            list.add("Churachandpur");
            list.add("Imphal East");
            list.add("Imphal West");
            list.add("Jiribam");
            list.add("Kakching");
            list.add("Kamjong");
            list.add("Kangpokpi");
            list.add("Noney");
            list.add("Pherzawl");
            list.add("Senapati");
            list.add("Tamenglong");
            list.add("Tengnoupal");
            list.add("Thoubal");
            list.add("Ukhrul");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Meghalaya")) {
            List<String> list = new ArrayList<String>();
            list.add("East Garo Hills");
            list.add("East Jaintia Hills");
            list.add("East Khasi Hills");
            list.add("North Garo Hills");
            list.add("Ri Bhoi");
            list.add("South Garo Hills");
            list.add("South West Garo Hills ");
            list.add("South West Khasi Hills");
            list.add("West Garo Hills");
            list.add("West Jaintia Hills");
            list.add("West Khasi Hills");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Mizoram")) {
            List<String> list = new ArrayList<String>();
            list.add("Aizawl");
            list.add("Champhai");
            list.add("Kolasib");
            list.add("Lawngtlai");
            list.add("Lunglei");
            list.add("Mamit");
            list.add("Saiha");
            list.add("Serchhip");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Nagaland")) {
            List<String> list = new ArrayList<String>();
            list.add("Dimapur");
            list.add("Kiphire");
            list.add("Kohima");
            list.add("Longleng");
            list.add("Mokokchung");
            list.add("Mon");
            list.add("Peren");
            list.add("Phek");
            list.add("Tuensang");
            list.add("Wokha");
            list.add("Zunheboto");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Odisha")) {
            List<String> list = new ArrayList<String>();
            list.add("Angul");
            list.add("Balangir");
            list.add("Balasore");
            list.add("Bargarh");
            list.add("Bhadrak");
            list.add("Boudh");
            list.add("Cuttack");
            list.add("Deogarh");
            list.add("Dhenkanal");
            list.add("Gajapati");
            list.add("Ganjam");
            list.add("Jagatsinghapur");
            list.add("Jajpur");
            list.add("Jharsuguda");
            list.add("Kalahandi");
            list.add("Kandhamal");
            list.add("Kendrapara");
            list.add("Kendujhar (Keonjhar)");
            list.add("Khordha");
            list.add("Koraput");
            list.add("Malkangiri");
            list.add("Mayurbhanj");
            list.add("Nabarangpur");
            list.add("Nayagarh");
            list.add("Nuapada");
            list.add("Puri");
            list.add("Rayagada");
            list.add("Sambalpur");
            list.add("Sonepur");
            list.add("Sundargarh");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Puducherry (UT)")) {
            List<String> list = new ArrayList<String>();
            list.add("Karaikal");
            list.add("Mahe");
            list.add("Pondicherry");
            list.add("Yanam");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Punjab")) {
            List<String> list = new ArrayList<String>();
            list.add("Amritsar");
            list.add("Barnala");
            list.add("Bathinda");
            list.add("Faridkot");
            list.add("Fatehgarh Sahib");
            list.add("Fazilka");
            list.add("Ferozepur");
            list.add("Gurdaspur");
            list.add("Hoshiarpur");
            list.add("Jalandhar");
            list.add("Kapurthala");
            list.add("Ludhiana");
            list.add("Mansa");
            list.add("Moga");
            list.add("Muktsar");
            list.add("Nawanshahr (Shahid Bhagat Singh Nagar)");
            list.add("Pathankot");
            list.add("Patiala");
            list.add("Rupnagar");
            list.add("Sahibzada Ajit Singh Nagar (Mohali)");
            list.add("Sangrur");
            list.add("Tarn Taran");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Rajasthan")) {
            List<String> list = new ArrayList<String>();
            list.add("Ajmer");
            list.add("Alwar");
            list.add("Banswara");
            list.add("Baran");
            list.add("Barmer");
            list.add("Bharatpur");
            list.add("Bhilwara");
            list.add("Bikaner");
            list.add("Bundi");
            list.add("Chittorgarh");
            list.add("Churu");
            list.add("Dausa");
            list.add("Dholpur");
            list.add("Dungarpur");
            list.add("Hanumangarh");
            list.add("Jaipur");
            list.add("Jaisalmer");
            list.add("Jalore");
            list.add("Jhalawar");
            list.add("Jhunjhunu");
            list.add("Jodhpur");
            list.add("Karauli");
            list.add("Kota");
            list.add("Nagaur");
            list.add("Pali");
            list.add("Pratapgarh");
            list.add("Rajsamand");
            list.add("Sawai Madhopur");
            list.add("Sikar");
            list.add("Sirohi");
            list.add("Sri Ganganagar");
            list.add("Tonk");
            list.add("Udaipur");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Sikkim")) {
            List<String> list = new ArrayList<String>();
            list.add("East Sikkim");
            list.add("North Sikkim");
            list.add("South Sikkim");
            list.add("West Sikkim");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Tamil Nadu")) {
            List<String> list = new ArrayList<String>();
            list.add("Ariyalur");
            list.add("Chennai");
            list.add("Coimbatore");
            list.add("Cuddalore");
            list.add("Dharmapuri");
            list.add("Dindigul");
            list.add("Erode");
            list.add("Kanchipuram");
            list.add("Kanyakumari");
            list.add("Karur");
            list.add("Krishnagiri");
            list.add("Madurai");
            list.add("Nagapattinam");
            list.add("Namakkal");
            list.add("Nilgiris");
            list.add("Perambalur");
            list.add("Pudukkottai");
            list.add("Ramanathapuram");
            list.add("Salem");
            list.add("Sivaganga");
            list.add("Thanjavur");
            list.add("Theni");
            list.add("Thoothukudi (Tuticorin)");
            list.add("Tiruchirappalli");
            list.add("Tirunelveli");
            list.add("Tiruppur");
            list.add("Tiruvallur");
            list.add("Tiruvannamalai");
            list.add("Tiruvarur");
            list.add("Vellore");
            list.add("Viluppuram");
            list.add("Virudhunagar");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Telangana")) {
            List<String> list = new ArrayList<String>();
            list.add("Adilabad");
            list.add("Bhadradri Kothagudem");
            list.add("Hyderabad");
            list.add("Jagtial");
            list.add("Jangaon");
            list.add("Jayashankar Bhoopalpally");
            list.add("Jogulamba Gadwal");
            list.add("Kamareddy");
            list.add("Karimnagar");
            list.add("Khammam");
            list.add("Komaram Bheem Asifabad");
            list.add("Mahabubabad");
            list.add("Mahabubnagar");
            list.add("Mancherial");
            list.add("Medak");
            list.add("Medchal");
            list.add("Nagarkurnool");
            list.add("Nalgonda");
            list.add("Nirmal");
            list.add("Nizamabad");
            list.add("Peddapalli");
            list.add("Rajanna Sircilla");
            list.add("Rangareddy");
            list.add("Sangareddy");
            list.add("Siddipet");
            list.add("Suryapet");
            list.add("Vikarabad");
            list.add("Wanaparthy");
            list.add("Warangal (Rural)");
            list.add("Warangal (Urban)");
            list.add("Yadadri Bhuvanagiri");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Tripura")) {
            List<String> list = new ArrayList<String>();
            list.add("Dhalai");
            list.add("Gomati");
            list.add("Khowai");
            list.add("North Tripura");
            list.add("Sepahijala");
            list.add("South Tripura");
            list.add("Unakoti");
            list.add("West Tripura");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("Uttarakhand")) {
            List<String> list = new ArrayList<String>();
            list.add("Almora");
            list.add("Bageshwar");
            list.add("Chamoli");
            list.add("Champawat");
            list.add("Dehradun");
            list.add("Haridwar");
            list.add("Nainital");
            list.add("Pauri Garhwal");
            list.add("Pithoragarh");
            list.add("Rudraprayag");
            list.add("Tehri Garhwal");
            list.add("Udham Singh Nagar");
            list.add("Uttarkashi");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }


        if(sp1.contentEquals("Uttar Pradesh")) {
            List<String> list = new ArrayList<String>();
            list.add("Agra");
            list.add("Aligarh");
            list.add("Allahabad");
            list.add("Ambedkar Nagar");
            list.add("Amethi (Chatrapati Sahuji Mahraj Nagar)");
            list.add("Amroha (J.P. Nagar)");
            list.add("Auraiya");
            list.add("Azamgarh");
            list.add("Baghpat");
            list.add("Bahraich");
            list.add("Ballia");
            list.add("Balrampur");
            list.add("Banda");
            list.add("Barabanki");
            list.add("Bareilly");
            list.add("Basti");
            list.add("Bhadohi");
            list.add("Bijnor");
            list.add("Budaun");
            list.add("Bulandshahr");
            list.add("Chandauli");
            list.add("Chitrakoot");
            list.add("Deoria");
            list.add("Etah");
            list.add("Etawah");
            list.add("Faizabad");
            list.add("Farrukhabad");
            list.add("Fatehpur");
            list.add("Firozabad");
            list.add("Gautam Buddha Nagar");
            list.add("Ghaziabad");
            list.add("Ghazipur");
            list.add("Gonda");
            list.add("Gorakhpur");
            list.add("Hamirpur");
            list.add("Hapur (Panchsheel Nagar)");
            list.add("Hardoi");
            list.add("Hathras");
            list.add("Jalaun");
            list.add("Jaunpur");
            list.add("Jhansi");
            list.add("Kannauj");
            list.add("Kanpur Dehat");
            list.add("Kanpur Nagar");
            list.add("Kanshiram Nagar (Kasganj)");
            list.add("Kaushambi");
            list.add("Kushinagar (Padrauna)");
            list.add("Lakhimpur - Kheri");
            list.add("Lalitpur");
            list.add("Lucknow");
            list.add("Maharajganj");
            list.add("Mahoba");
            list.add("Mainpuri");
            list.add("Mathura");
            list.add("Mau");
            list.add("Meerut");
            list.add("Mirzapur");
            list.add("Moradabad");
            list.add("Muzaffarnagar");
            list.add("Pilibhit");
            list.add("Pratapgarh");
            list.add("RaeBareli");
            list.add("Rampur");
            list.add("Saharanpur");
            list.add("Sambhal (Bhim Nagar)");
            list.add("Sant Kabir Nagar");
            list.add("Shahjahanpur");
            list.add("Shamali (Prabuddh Nagar)");
            list.add("Shravasti");
            list.add("Siddharth Nagar");
            list.add("Sitapur");
            list.add("Sonbhadra");
            list.add("Sultanpur");
            list.add("Unnao");
            list.add("Varanasi");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }

        if(sp1.contentEquals("West Bengal")) {
            List<String> list = new ArrayList<String>();
            list.add("Alipurduar");
            list.add("Bankura");
            list.add("Birbhum");
            list.add("Burdwan (Bardhaman)");
            list.add("Cooch Behar");
            list.add("Dakshin Dinajpur (South Dinajpur)");
            list.add("Darjeeling");
            list.add("Hooghly");
            list.add("Howrah");
            list.add("Jalpaiguri");
            list.add("Kalimpong");
            list.add("Kolkata");
            list.add("Malda");
            list.add("Murshidabad");
            list.add("Nadia");
            list.add("North 24 Parganas");
            list.add("Paschim Medinipur (West Medinipur)");
            list.add("Purba Medinipur (East Medinipur)");
            list.add("Purulia");
            list.add("South 24 Parganas");
            list.add("Uttar Dinajpur (North Dinajpur)");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            distSpinner.setAdapter(dataAdapter);
        }



    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
