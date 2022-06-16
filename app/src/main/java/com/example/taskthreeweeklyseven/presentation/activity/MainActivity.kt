package com.example.taskthreeweeklyseven.presentation.activity

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskthreeweeklyseven.R
import com.example.taskthreeweeklyseven.data.dto.ImageResponce
import com.example.taskthreeweeklyseven.data.dto.VoteRequest
import com.example.taskthreeweeklyseven.databinding.ActivityMainBinding
import com.example.taskthreeweeklyseven.presentation.fragment.CatFragment
import com.example.taskthreeweeklyseven.presentation.utils.AppState
import com.example.taskthreeweeklyseven.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var post: ImageResponce

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        onClick()
    }

    private fun onClick() {
        binding.cardLike.setOnClickListener {
            viewModel.postVote(createCat(like))
            viewModel.saveFavorite(post)
            viewModel.getData()
        }

        binding.cardDislike.setOnClickListener {
            viewModel.postVote(createCat(dislike))
            viewModel.getData()
        }
    }

    private fun createCat(params: Int): VoteRequest {
        return VoteRequest(
            post.id,
            subId,
            params
        )
    }

    private fun initViewModel() {
        viewModel = MainViewModel()
        viewModel.liveDataPost.observe(this) { renderData(it) }
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessMain -> {
                post = appState.imageResponse[first]
                val uri = Uri.parse(post.url)
                binding.image.setImageURI(uri)
            }
            is AppState.Error -> {
                Toast.makeText(this, appState.e.message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tinder_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, CatFragment())
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val first = 0
        private const val subId = "from-phone"
        private const val like = 1
        private const val dislike = 0
    }
}