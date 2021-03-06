/*
* Copyright (C) 2014 MediaTek Inc.
* Modification based on code covered by the mentioned copyright
* and/or permission notice(s).
*/
/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.development;

import java.util.List;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.os.Bundle;

public class Development extends LauncherActivity
{
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        LinearLayout topPanel = (LinearLayout) findViewById(com.android.internal.R.id.topPanel);
        if (topPanel != null) {
            topPanel.setVisibility(View.GONE);
        }
    }

    @Override
    protected Intent getTargetIntent() {
        Intent targetIntent = new Intent(Intent.ACTION_MAIN, null);
        targetIntent.addCategory(Intent.CATEGORY_TEST);
        return targetIntent;
    }

    protected void onSortResultList(List<ResolveInfo> results) {
        super.onSortResultList(results);
        Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        List<ResolveInfo> topItems = getPackageManager().queryIntentActivities(
                settingsIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (topItems != null) {
            super.onSortResultList(topItems);
            results.addAll(0, topItems);
        }
    }
}
