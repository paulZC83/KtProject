package cn.sh.changxing.materialtest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.sh.changxing.materialtest.kt.showToast
import com.bumptech.glide.Glide

class FruitAdapter(val context: Context, val fruitList:List<Fruit>):RecyclerView.Adapter<FruitAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName:TextView = view.findViewById(R.id.fruitName)
        val ivImage:ImageView = view.findViewById(R.id.fruitImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val holder = MyViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
//            "您点击了第$position".showToast(context)
            R.string.app_name.showToast(context, Toast.LENGTH_LONG)
            val fruit = fruitList[position]
            val intent = Intent(context, FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount(): Int {
       return fruitList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = fruitList[position].name
        Glide.with(context).load(fruitList[position].imageId).into(holder.ivImage)
    }
}