package com.example.project3

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.project3.fragments.DBClearDialogFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Settings"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            val themeSet = findPreference<ListPreference>(   getString(R.string.key_theme))
            val dbClear = findPreference<Preference>(getString(R.string.pref_db_clea))

            themeSet?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                setTheme(newValue as String)
                return@OnPreferenceChangeListener true
            }

            dbClear?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                Log.d("DB", "Clearing DB!")
                val dialog = DBClearDialogFragment()
                activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "dbclear") }
                return@OnPreferenceClickListener true
            }
        }

        private fun setTheme(value: String) {
            Log.d("THEME", "Theme set to: $value")
            when (value) {
                "Auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}