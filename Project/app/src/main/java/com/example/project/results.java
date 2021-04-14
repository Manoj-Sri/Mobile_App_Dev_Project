package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.parceler.Parcels;

import java.util.ArrayList;

public class results extends AppCompatActivity {

    PieChart pie_chart;

    ArrayList<Contestant> bundle_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        pie_chart = findViewById(R.id.pie_chart);
        pie_chart.setUsePercentValues(true);
        pie_chart.getDescription().setEnabled(false);

        pie_chart.setDragDecelerationFrictionCoef(0.5f);
        pie_chart.setDrawHoleEnabled(true);
        pie_chart.setHoleColor(Color.WHITE);
        pie_chart.setTransparentCircleRadius(30f);



        Parcelable p = getIntent().getParcelableExtra("parse_key");
        bundle_data = Parcels.unwrap(p);


        ArrayList<PieEntry> entries = new ArrayList<>();

        for(Contestant c : bundle_data) {
            String name = c.getName();
            String score = c.getScore();
            Integer int_score = Integer.parseInt(score);
            entries.add(new PieEntry(int_score,name));
        }

        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(pieDataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pie_chart.setData(data);



    }

}