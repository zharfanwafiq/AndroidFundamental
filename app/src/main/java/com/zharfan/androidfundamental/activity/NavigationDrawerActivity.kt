package com.zharfan.androidfundamental.activity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.zharfan.androidfundamental.R

import com.zharfan.androidfundamental.databinding.ActivityNavigationDrawerBinding
import de.hdodenhof.circleimageview.CircleImageView

class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding
    private lateinit var profileCircleImageView: CircleImageView
    private var profileImage = "https://lh3.googleusercontent.com/-4qy2DfcXBoE/AAAAAAAAAAI/AAAAAAAABi4/rY-jrtntAi4/s640-il/photo.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setData()

    }

    private fun setData() {
        binding.apply {
            setSupportActionBar(appBarNavigationDrawer.toolbar)

            appBarNavigationDrawer.fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action") {
                        Toast.makeText(this@NavigationDrawerActivity, "HAllo", Toast.LENGTH_SHORT).show()
                    }
                    .show()
            }
            val drawerLayout: DrawerLayout = drawerLayout
            val navView: NavigationView = navView
            val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.

            profileCircleImageView = navView.getHeaderView(0).findViewById(R.id.imageView)
            Glide.with(this@NavigationDrawerActivity)
                .load(profileImage)
                .into(profileCircleImageView)

            appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_cart), drawerLayout)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}