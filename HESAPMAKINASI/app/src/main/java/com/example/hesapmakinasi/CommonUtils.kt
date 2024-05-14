package com.example.hesapmakinasi

object CommonUtils {
// her yerden ulasabilmek için object sınıfı olusturduk
    fun formatKronometre(millisecond: Long): String {
        val secondsCalculate = millisecond / 1000
        val hour = secondsCalculate / 3600
        val minutes = secondsCalculate / 360
        val seconds = secondsCalculate % 60
        val milliseconds = (millisecond % 1000) / 10
        return String.format("%02d:%02d:%02d:%02d", hour, minutes, seconds, milliseconds)

        // %: Yer tutucunun başlangıcını belirtir.
        // 0: sayının önüne gerekirse sıfır ekler. Yani, sayı hedeflenen hane sayısından daha az basamağa sahipse, başına sıfır eklenir.
        // 2: en az 2 karakterlik bir alan ayrılır
        // d: Yer tutucunun tamsayı değeri temsil ettiğini belirtir.
    }


}