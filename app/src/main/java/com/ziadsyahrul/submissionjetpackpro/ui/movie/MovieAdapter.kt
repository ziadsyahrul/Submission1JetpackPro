package com.ziadsyahrul.submissionjetpackpro.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ziadsyahrul.submissionjetpackpro.databinding.ItemListBinding
import com.ziadsyahrul.submissionjetpackpro.model.MovieModel
import com.ziadsyahrul.submissionjetpackpro.ui.detail.DetailActivity
import com.ziadsyahrul.submissionjetpackpro.ui.detail.DetailViewModel.Companion.MOVIE

class MovieAdapter(private val movies: ArrayList<MovieModel>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    fun setMovie(movies: ArrayList<MovieModel>){
        if (movies.isNullOrEmpty()) return
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel){
            with(binding){
                tvItemTitle.text = movie.title
                year.text = movie.year
                Picasso.get().load(movie.poster).into(imgPoster)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_FILM, movie.id)
                intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)
                itemView.context.startActivity(intent)
            }
        }
    }
}