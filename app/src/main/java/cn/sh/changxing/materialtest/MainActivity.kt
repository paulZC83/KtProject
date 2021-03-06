package cn.sh.changxing.materialtest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import cn.sh.changxing.materialtest.kt.showSnack
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()
    private val fruits = mutableListOf(Fruit("Apple", R.drawable.apple),
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Grape", R.drawable.grape),
        Fruit("Mango", R.drawable.mango),
        Fruit("Orange", R.drawable.orange),
        Fruit("Pear", R.drawable.pear),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Watermelon", R.drawable.watermelon)
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }
        fab.setOnClickListener {
            view ->
//            Snackbar.make(view, "Delete Data", Snackbar.LENGTH_SHORT).setAction("Undo") {
//                Toast.makeText(this, "Data restore", Toast.LENGTH_SHORT).show()
//            }.show()
            view.showSnack("Delete Data", "Undo") {
                Toast.makeText(this, "Data restore", Toast.LENGTH_SHORT).show()
            }
        }

        initFruit()
        val glideManager = GridLayoutManager(this, 2)
        recyclerview.layoutManager = glideManager
        val adapter = FruitAdapter(this, fruitList)
        recyclerview.adapter = adapter

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruit(adapter)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.backup ->
                Toast.makeText(this, "you click backup menu!", Toast.LENGTH_SHORT).show()

            R.id.delete ->
                Toast.makeText(this, "you click delete menu!", Toast.LENGTH_SHORT).show()

            R.id.setting ->
                Toast.makeText(this, "you click setting menu!", Toast.LENGTH_SHORT).show()

            android.R.id.home ->
                drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    private fun initFruit(){
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    private fun refreshFruit(adapter: FruitAdapter){
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruit()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

}
