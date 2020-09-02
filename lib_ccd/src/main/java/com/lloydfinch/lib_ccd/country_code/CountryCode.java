package com.lloydfinch.lib_ccd.country_code;


import com.lloydfinch.lib_ccd.lib_tools.action.Stable;

import java.io.Serializable;
import java.util.List;

/**
 * Name: CountryCode
 * Author: lloydfinch
 * Function: CountryCode，国家/地区解析类
 * Date: 2020-06-12 11:04
 * Modify: lloydfinch 2020-06-12 11:04
 */
public class CountryCode implements Stable {

    private String title = "";
    private List<CodeInfo> data;

    public String getTitle() {
        return title;
    }

    public List<CodeInfo> getData() {
        return data;
    }

    public static class CodeInfo implements Serializable, Stable {
        private String name = "";
        private String zh_name = "";
        private String code_number = "";
        private String zh_full_char = "";
        private String code = "";
        private String caps = ""; //首字母
        private boolean isCap = false; //判断是否是首字母

        public CodeInfo() {
            isCap = false;
        }

        public CodeInfo(String caps) {
            this.caps = caps;
            this.isCap = true;
        }

        public String getName() {
            return name;
        }

        public String getZh_name() {
            return zh_name;
        }

        public String getCode_number() {
            return code_number;
        }

        public String obtainCode() {
            return "+" + code_number;
        }

        public String getZh_full_char() {
            return zh_full_char;
        }

        public String getCode() {
            return code;
        }

        public boolean isCap() {
            return isCap;
        }

        public String getCaps() {
            return caps;
        }

        public void setCaps(String caps) {
            this.caps = caps;
        }

        public void setCap(boolean cap) {
            isCap = cap;
        }

        public String getDes() {
            return getName() + " +" + getCode_number();
        }

        @Override
        public String toString() {
            return "CCP{" +
                    "name='" + name + '\'' +
                    ", zh_name='" + zh_name + '\'' +
                    ", code_number='" + code_number + '\'' +
                    ", zh_full_char='" + zh_full_char + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}
