package com.lloydfinch.counrtycode;

import android.content.Intent;
import android.os.Bundle;

import com.lloydfinch.lib_ccd.LConstants;
import com.lloydfinch.lib_ccd.country_code.CountryCode;
import com.lloydfinch.lib_ccd.country_code.CountryCodeActivity;
import com.lloydfinch.lib_ccd.lib_tools.tips.Logger;

import androidx.annotation.Nullable;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            CountryCodeActivity.LaunchForResult(this, LConstants.START_COUNTRY_CODE, null);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LConstants.START_COUNTRY_CODE && resultCode == LConstants.RESULT_COUNTRY_CODE) {
            if (data != null) {
                CountryCode.CodeInfo code = (CountryCode.CodeInfo) data.getSerializableExtra(LConstants.COUNTRY_CODE);
                Logger.e(TAG, code);
            }
        }
    }
}
