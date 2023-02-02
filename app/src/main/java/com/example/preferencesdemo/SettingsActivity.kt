package com.example.preferencesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,SettingFragment())
            .commit()
    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat,
        pref: Preference
    ): Boolean {
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(classLoader,pref.fragment!!)
        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .addToBackStack(null)
            .commit()

        supportFragmentManager.setFragmentResultListener("xy",fragment){key,bundle ->
            if(key == "xy"){
                supportFragmentManager.setFragmentResult("xy", bundle)
            }


        }

       return true
    }


}