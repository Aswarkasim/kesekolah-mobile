package com.scrollupstudio.kesekolah.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.stringPref

class PrefsManager(context: Context): Krate {

    private val PREF_IS_LOGIN: String = "prefs_is_login"
    private val PREFS_ID_USER: String = "prefs_id_user"
    private val PREFS_USERNAME: String = "prefs_username"
    private val PREFS_NAMALENGKAP: String = "prefs_namalengkap"
    private val PREFS_ROLE: String = "prefs_role"
    private val PREFS_IS_ACTIVE: String = "prefs_is_active"

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(
            "scrollupstudio_kesekolah_prefs123456", Context.MODE_PRIVATE
        )
    }

    var prefsIsLogin by booleanPref(PREF_IS_LOGIN, false)
    var prefsIdUser by stringPref(PREFS_ID_USER, "")
    var prefsUsername by stringPref(PREFS_USERNAME, "")
    var prefsNamalengkap by stringPref(PREFS_NAMALENGKAP, "")
    var prefsRole by stringPref(PREFS_ROLE, "")
    var prefsIsActive by stringPref(PREFS_IS_ACTIVE, "")

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }
}