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










MIT License

Copyright (c) 2020 EasySkilling

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
