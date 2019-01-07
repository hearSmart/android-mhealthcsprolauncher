package com.hearxgroup.mhealthcsprolauncher

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            startActivity(
                getLaunchAppIntent(
                    getPackageManager(),
                    null,
                    "com.hearxgroup.mhealthstudio.intent.action.testrequestfromfile"
                )
            )
        } catch(error: NullPointerException) {
            Toast.makeText(this, "Missing valid mHealth app!", Toast.LENGTH_LONG).show()
        }

    }

    fun getLaunchAppIntent(packageManager: PackageManager, bundle: Bundle?, actionName: String): Intent? {
        val newIntent = Intent(actionName)
        newIntent.type = "text/plain"
        val activities = packageManager.queryIntentActivities(newIntent, 0)
        val isIntentSafe = activities.size > 0
        if (isIntentSafe) {
            if (bundle != null) {
                newIntent.putExtras(bundle)
            }

            newIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            newIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return newIntent
        } else {
            return null
        }
    }
}
