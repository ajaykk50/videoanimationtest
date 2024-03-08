package com.example.videoanimation

import android.graphics.Rect
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphamovie.lib.AlphaMovieView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var textureView: TextureView
    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textureView = findViewById(R.id.textureView)


        val alphaMovieView: AlphaMovieView = findViewById(R.id.player)
        val giftsButton: FloatingActionButton = findViewById(R.id.giftsButton)

        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)

        giftsButton.setOnClickListener {
            alphaMovieView.setVideoFromUri(this, videoUri)
        }


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val adapter = GridAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(GridItemDecoration())
        val imageList = mutableListOf(
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.fourth,
            R.drawable.fifth,
            R.drawable.sixth
        )
        adapter.setData(imageList)


        val rvComments: RecyclerView = findViewById(R.id.rvComments)
        val itemList = listOf(
            Item("https://example.com/image1.jpg", "Item 1"),
            Item("https://example.com/image2.jpg", "Item 2"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
            Item("https://example.com/image3.jpg", "Item 3"),
        )
        rvComments.layoutManager = LinearLayoutManager(this)
        val commentsAdapter = CommentsAdapter(this, itemList)
        rvComments.adapter = commentsAdapter
    }

    private class GridAdapter : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

        private var data: MutableList<Int> = mutableListOf()

        fun setData(newData: MutableList<Int>) {
            data = newData
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val imageUrl = data[position]
            // Load your image using an image loading library (e.g., Picasso, Glide)
            // Replace "Picasso.get().load()" with your actual image loading code
            // Picasso.get().load(imageUrl).into(holder.imageView)

            holder.imageView.setImageResource(imageUrl)
        }

        override fun getItemCount(): Int = data.size

        // ViewHolder class
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.gridImageView)
        }
    }

    // GridItemDecoration class to handle spacing between items
    private class GridItemDecoration : RecyclerView.ItemDecoration() {
        private val spacing = 10 // Adjust spacing as needed

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = spacing
            outRect.right = spacing
            outRect.top = spacing
            outRect.bottom = spacing
        }
    }
}