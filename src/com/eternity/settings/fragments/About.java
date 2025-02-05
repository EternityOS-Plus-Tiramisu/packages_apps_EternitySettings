/*
 * Copyright (C) 2022 Eternity-OS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eternity.settings.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import com.android.settings.R;

import java.util.List;
import java.util.ArrayList;

@SearchIndexable
public class About extends SettingsPreferenceFragment {

    public static final String TAG = "About";

    private String KEY_ETERNITY_SOURCE = "eternity_source";
    private String KEY_ETERNITY_TELEGRAM = "eternity_telegram";
    private String KEY_ETERNITY_TELEGRAM_CHANNEL = "eternity_telegram_channel";

    private Preference mSourceUrl;
    private Preference mTelegramUrl;
    private Preference mTelegramChannelUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.eternity_settings_about);

        mSourceUrl = findPreference(KEY_ETERNITY_SOURCE);
        mTelegramUrl = findPreference(KEY_ETERNITY_TELEGRAM);
        mTelegramChannelUrl = findPreference(KEY_ETERNITY_TELEGRAM_CHANNEL);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mSourceUrl) {
            launchUrl("https://github.com/EternityOS-Plus");
        } else if (preference == mTelegramUrl) {
            launchUrl("https://t.me/eternity_os");
        } else if (preference == mTelegramChannelUrl) {
            launchUrl("https://t.me/eternity_updates");
        }

        return super.onPreferenceTreeClick(preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(intent);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.ETERNITY_SETTINGS;
    }

    /**
     * For search
     */
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.eternity_settings_about);
}
