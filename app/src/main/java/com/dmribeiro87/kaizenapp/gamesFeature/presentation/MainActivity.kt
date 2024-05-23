package com.dmribeiro87.kaizenapp.gamesFeature.presentation

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmribeiro87.kaizenapp.R
import com.dmribeiro87.kaizenapp.core.util.LiveDataInternetConnections
import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.core.util.hide
import com.dmribeiro87.kaizenapp.core.util.show
import com.dmribeiro87.kaizenapp.databinding.ActivityMainBinding
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports
import com.dmribeiro87.kaizenapp.gamesFeature.presentation.adapters.SportsAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SportsViewModel by viewModels()
    private val connection: LiveDataInternetConnections by lazy { LiveDataInternetConnections(application) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel.getSportsEvents()
        addObservers()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        checkInternetConnection()
    }

    private fun addObservers() {
        viewModel.sportsData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showLoading()
                }

                is Resource.Success -> {
                    resource.data?.let { showSuccess(it) }
                }

                is Resource.Error -> {
                    showError(resource.message.toString())
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.show()
        binding.rvSports.hide()
        binding.llError.hide()
    }

    private fun showError(message: String) {
        binding.progressBar.hide()
        binding.llError.show()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showSuccess(sports: List<Sports>) {
        binding.progressBar.hide()
        binding.rvSports.show()
        binding.llError.hide()
        setupRecyclerView(sports)
    }

    private fun setupRecyclerView(sports: List<Sports>) {
        binding.rvSports.layoutManager = LinearLayoutManager(this)
        val sportsAdapter = SportsAdapter(sports)
        sportsAdapter.setAction { event ->
            viewModel.updateFavorite(event)
        }
        binding.rvSports.adapter = sportsAdapter
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showSnackBar(message: String, color: Int, length: Int) {
        Snackbar.make(binding.root, message, length)
            .setAction("OK", null)
            .setBackgroundTint(color).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkInternetConnection() {
        connection.observe(this) { isConnected ->
            if (isConnected) {
                showSnackBar(
                    getString(R.string.text_connected), ContextCompat.getColor(
                       this,
                        R.color.switch_green
                    ), Snackbar.LENGTH_SHORT
                )
                viewModel.getSportsEvents()
            } else {
                showSnackBar(
                    getString(R.string.error_no_connection_data_could_be_out_dated),
                    ContextCompat.getColor(this, R.color.brand_red),
                    Snackbar.LENGTH_INDEFINITE
                )
            }
        }
    }
}