package cn.sh.changxing.materialtest.kt

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun main() {
    val a = 2
    val b = 7
    val c = -9
    val d = 88
    val e = -99
    println("max value id ${max(a,b,c,d,e)}")

    val x = 3.5
    val y = 7.9
    val z = -9.0
    println("max float is ${max(x, y, z)}")

}


fun max(vararg nums:Int):Int {
    var maxNum = Int.MIN_VALUE
    for (num in nums) {
        maxNum = kotlin.math.max(maxNum, num)
    }
    return maxNum
}

fun <T:Comparable<T>> max(vararg nums:T):T{
    if (nums.isEmpty()) throw RuntimeException("Params can not be empty")
    var maxNum = nums[0]
    for (num in nums) {
        if (num > maxNum) {
            maxNum = num
        }
    }
    return maxNum
}

fun String.showToast(context: Context, duration:Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration:Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun View.showSnack(text:String, actionText:String, duration:Int = Snackbar.LENGTH_SHORT, block:(()->Unit)?=null){
    val snackbar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackbar.setAction(actionText) {
            block()
        }
    }
    snackbar.show()
}
fun View.showSnack(text:Int, actionResId:Int, duration:Int = Snackbar.LENGTH_SHORT, block:(()->Unit)?=null){
    val snackbar = Snackbar.make(this, text, duration)
    if (actionResId != null && block != null) {
        snackbar.setAction(actionResId) {
            block()
        }
    }
    snackbar.show()
}