package edu.msudenver.cs3013.project1_s24

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

const val GAME_ID = "GAME_ID"
interface VideoGameListener {
    fun onSelected(id: Int)
}
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navController = findNavController(R.id.nav_host_fragment)
        bottomNavView = findViewById(R.id.bottom_nav_view)
        bottomNavView.setupWithNavController(navController)

        // Set the OnNavigationItemSelectedListener
        bottomNavView.setOnNavigationItemSelectedListener { item ->
            // Retrieve user's information from SharedPreferences
            val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val username = sharedPref.getString("username", "")
            val email = sharedPref.getString("email", "")
            val gameCompany = sharedPref.getString("gameCompany", "")
            val gameIdea = sharedPref.getString("gameIdea", "")
            val gameDescription = sharedPref.getString("gameDescription", "")

            when (item.itemId) {
                R.id.usernameInformationFragment -> {
                    Toast.makeText(this, "Username: $username\nEmail: $email", Toast.LENGTH_LONG).show()
                }
                R.id.gameCompanyListFragment -> {
                    Toast.makeText(this, "Game Company: $gameCompany", Toast.LENGTH_LONG).show()
                }
                R.id.videogameConceptsFragment -> {
                    Toast.makeText(this, "Game Idea: $gameIdea\nGame Description: $gameDescription", Toast.LENGTH_LONG).show()
                }
            }

            // Let the NavController handle the navigation
            NavigationUI.onNavDestinationSelected(item, navController)
        }
    }
    // ...
}