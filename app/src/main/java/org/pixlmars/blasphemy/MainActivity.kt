package org.pixlmars.blasphemy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.pixlmars.blasphemy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        binding.requestButton.setOnClickListener {
            lifecycleScope.launch {
                val response = withContext(Dispatchers.IO) {
                    apiService.getRandomAnime()
                }

                response.body()?.data?.let { animeData ->
                    Glide.with(this@MainActivity)
                        .load(animeData.images.jpg.image_url)
                        .into(binding.animeImageView)
                    binding.genres.text = animeData.genres.joinToString { it.name }
                    binding.releaseYear.text = "Release year: ${animeData.aired.prop.from.year}"
                    binding.titleEnglish.text = "Anime name: ${animeData.title}"
                    binding.titleOriginal.text = "(${animeData.title_japanese})"
                }
            }
        }
    }
}
