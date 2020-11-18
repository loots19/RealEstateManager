package com.openclassrooms.realestatemanager.detailActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.openclassrooms.realestatemanager.ConversionActivity
import com.openclassrooms.realestatemanager.R


class DetailActivity : AppCompatActivity() {

    @BindView(R.id.rv_detail)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.imageView_convert)
    lateinit var ivConvert: ImageView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)


        ivConvert.setOnClickListener {
            launchConvertActivity()
        }


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        // adding inbuilt divider line
        // recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL))


        val images = ArrayList<Image>()

        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.logo, "logo"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.logo, "logo"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.agency, "dollars"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.logo, "logo"))
        images.add(Image(R.drawable.dollar, "dollars"))
        images.add(Image(R.drawable.agency, "dollars"))


        val adapter = DetailAdapter(images, this) { item ->
            val intent = Intent(this, FullScreenActivity::class.java)
            intent.putExtra("iImage", item.image)
            this.startActivity(intent)
        }


        recyclerView.adapter = adapter
    }
    private fun launchConvertActivity(){
        startActivity(Intent(this@DetailActivity, ConversionActivity:: class.java))

    }

}
