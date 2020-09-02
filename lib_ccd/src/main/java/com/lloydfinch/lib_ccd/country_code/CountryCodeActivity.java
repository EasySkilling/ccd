package com.lloydfinch.lib_ccd.country_code;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.lloydfinch.lib_ccd.LConstants;
import com.lloydfinch.lib_ccd.R;
import com.lloydfinch.lib_ccd.databinding.ActivityCountryCodeBinding;
import com.lloydfinch.lib_ccd.lib_tools.io.FileTool;
import com.lloydfinch.lib_ccd.lib_tools.io.GsonParser;
import com.lloydfinch.lib_ccd.lib_tools.thread.ThreadTool;
import com.lloydfinch.lib_ccd.lib_tools.tips.Logger;
import com.lloydfinch.lib_ccd.lib_tools.tips.Toaster;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 选择国家/地区
 */
public class CountryCodeActivity extends AppCompatActivity {

    private ActivityCountryCodeBinding binding;
    private volatile List<CountryCode.CodeInfo> mCodeInfos = new ArrayList<>();
    private CountryCodeAdapter codeAdapter = new CountryCodeAdapter(mCodeInfos);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCountryCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init(this);
    }

    private void init(Context context) {
        parseCCP(context);
        initView();
        initData();
    }

    private void initView() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.recyclerView.setAdapter(codeAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //选择国家码
        codeAdapter.setOnCountryCodeClick(codeInfo -> {
            Logger.d(codeInfo);
            binding.tvCurrentCountry.setText(codeInfo.getDes());
            Intent intent = getIntent();
            if (intent != null) {
                intent.putExtra(LConstants.COUNTRY_CODE, codeInfo);
                setResult(LConstants.RESULT_COUNTRY_CODE, intent);
                finish();
            }
        });

        //快速定位
        binding.fastLocationView.setOnItemClick((position, cap) -> {
            //提示
            Toaster.showShort(cap);
            if (mCodeInfos != null && !mCodeInfos.isEmpty()) {
                //取到对应字母的国家集合
                for (CountryCode.CodeInfo info : mCodeInfos) {
                    if (info == null || !info.isCap()) { //不是首字母就跳过
                        continue;
                    }
                    //如果首字母相同，找位置
                    if (TextUtils.equals(info.getCaps(), cap)) {
                        //计算实际位置
                        int realPosition = mCodeInfos.indexOf(info);
                        //滑动到实际位置
                        LinearLayoutManager layoutManager = (LinearLayoutManager) binding.recyclerView.getLayoutManager();
                        if (layoutManager != null) {
                            layoutManager.scrollToPositionWithOffset(realPosition, 0);
                        }
                    }
                }
            }
        });

        //热搜索
        binding.etCountryCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //实现热过滤
                if (s != null) {
                    String key = s.toString();
                    if (!TextUtils.isEmpty(key)) {
                        List<CountryCode.CodeInfo> codeInfos = search(key);
                        codeAdapter.refresh(codeInfos);
                    } else {
                        codeAdapter.refresh(mCodeInfos);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 解析数据，得到国家码
     */
    private void parseCCP(Context context) {
        ThreadTool.postAsyncSingle(() -> {
            //解析得到国家码
            InputStream inputStream = context.getResources().openRawResource(R.raw.cpp_english);
            String json = FileTool.getDatafromInputStream(inputStream);
            List<CountryCode> countryCodes = GsonParser.parseToList(json, CountryCode.class);

            //刷数据
            ThreadTool.postToUI(() -> {
                //首字母栏
                List<String> titles = new ArrayList<>();
                //国家码栏
                List<CountryCode.CodeInfo> codeInfos = new ArrayList<>();
                for (CountryCode countryCode : countryCodes) {
                    titles.add(countryCode.getTitle());

                    //根据首字母构造添加
                    codeInfos.add(new CountryCode.CodeInfo(countryCode.getTitle()));
                    //添加首字母下的国家栏
                    List<CountryCode.CodeInfo> data = countryCode.getData();
                    codeInfos.addAll(data);
                }

                mCodeInfos.clear();
                mCodeInfos.addAll(codeInfos);

                binding.fastLocationView.refresh(titles);
                codeAdapter.refresh(mCodeInfos);
            });
        });
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            CountryCode.CodeInfo codeInfo = (CountryCode.CodeInfo) intent.getSerializableExtra(LConstants.COUNTRY_CODE);
            if (codeInfo != null) {
                binding.tvCurrentCountry.setText(codeInfo.getDes());
            }
        }
    }

    /**
     * 检索国家码
     *
     * @param keyword 关键词
     * @return 符合国家码的列表
     */
    private List<CountryCode.CodeInfo> search(String keyword) {
        List<CountryCode.CodeInfo> filters = new ArrayList<>();
        for (CountryCode.CodeInfo codeInfo : mCodeInfos) {
            if (codeInfo == null || codeInfo.isCap()) {
                continue;
            }
            //首字母相同
            if (keyword.equalsIgnoreCase(codeInfo.getCaps())) {
                if (!filters.contains(codeInfo)) {
                    filters.add(codeInfo);
                }
            }
            //名字被包含
            if (codeInfo.getName().contains(keyword)) {
                if (!filters.contains(codeInfo)) {
                    filters.add(codeInfo);
                }
            }
            //地区码被包含
            if (codeInfo.getCode_number().contains(keyword)) {
                if (!filters.contains(codeInfo)) {
                    filters.add(codeInfo);
                }
            }
        }

        return filters;
    }

    public static void Launch(Context context) {
        Intent intent = new Intent(context, CountryCodeActivity.class);
        context.startActivity(intent);
    }

    /**
     * @param activity
     * @param reqCode
     * @param codeInfo 当前地区码，没有就传null
     */
    public static void LaunchForResult(Activity activity, int reqCode, CountryCode.CodeInfo codeInfo) {
        Intent intent = new Intent(activity, CountryCodeActivity.class);
        intent.putExtra(LConstants.COUNTRY_CODE, codeInfo);
        activity.startActivityForResult(intent, reqCode);

    }
}
