/***
Copyright (c) 2012 CommonsWare, LLC

Licensed under the Apache License, Version 2.0 (the "License"); you may
not use this file except in compliance with the License. You may obtain
a copy of the License at
  http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.commonsware.cwac.strict.demo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.commonsware.cwac.strict.StrictAdapter;

public class StrictModeExDemoActivity extends ListActivity {
  private static final String[] items= { "lorem", "ipsum", "dolor",
      "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi",
      "vel", "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam",
      "vel", "erat", "placerat", "ante", "porttitor", "sodales",
      "pellentesque", "augue", "purus" };
  private StrictAdapter wrapper=null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ArrayAdapter<String> base=
        new ArrayAdapter<String>(this,
                                 android.R.layout.simple_list_item_1,
                                 items);

    wrapper=new StrictAdapter(base);
    setListAdapter(wrapper);
  }

  @Override
  public void onResume() {
    super.onResume();
    wrapper.reset();
  }

  @Override
  public void onPause() {
    wrapper.dumpResultsToLog();
    super.onPause();
  }
}