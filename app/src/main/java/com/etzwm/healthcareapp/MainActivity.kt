package com.etzwm.healthcareapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_top_bar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_start,
            R.id.navigation_find_specialists,
            R.id.navigation_medical_checkup_packages,
            R.id.navigation_hospital_contacts,
            R.id.navigation_recommend_suggest))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        supportActionBar?.hide()

        EmergencyCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:01500005")
            startActivity(intent)
        }

//        setSupportActionBar(AppToolbar)

//        AppToolbar.setNavigationOnClickListener(View.OnClickListener {
//            fun onClick(view: View) {
//                FragmentManager.POP_BACK_STACK_INCLUSIVE
//                AppToolbar.navigationIcon = null
//                AppToolbar.setNavigationIcon(null)
//            }
//        })
    }
}
