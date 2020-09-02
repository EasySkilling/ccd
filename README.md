### How to Use:

send request
```
//codeInfo is the current selected country code
CountryCodeActivity.LaunchForResult(this, LConstants.START_COUNTRY_CODE, codeInfo);
```

handle response
```
@Override
public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    //judge the result if a country code request
    if (requestCode == LConstants.START_COUNTRY_CODE && resultCode == LConstants.RESULT_COUNTRY_CODE) {
        if (data != null) {
            //get codeInfo
            CountryCode.CodeInfo code = (CountryCode.CodeInfo) data.getSerializableExtra(LConstants.COUNTRY_CODE);
            Logger.e(TAG, code);
        }
    }
}
```
