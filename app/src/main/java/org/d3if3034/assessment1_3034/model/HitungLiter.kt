package org.d3if3034.assessment1_3034.model

fun JumlahHarga.HitungLiter(): JumlahLiter {
    val jumlahHarga = jumlahHarga
    val jenis =
        if (jenisBbm.equals("Pertalite", ignoreCase = true)) {
            jumlahHarga.toFloat() / 10000
        } else if (jenisBbm.equals("Pertamax", ignoreCase = true)) {
            jumlahHarga.toFloat() / 13550
        } else if (jenisBbm.equals("Solar", ignoreCase = true)) {
            jumlahHarga.toFloat() / 15250
        } else {
            0f
        }

    val hargaPerliter =
        if (jenisBbm.equals("Pertalite", ignoreCase = true)) {
            10000
        } else if (jenisBbm.equals("Pertamax", ignoreCase = true)) {
            13550
        } else if (jenisBbm.equals("Solar", ignoreCase = true)) {
            15250
        } else {
            0
        }

    return JumlahLiter(jenis, hargaPerliter)
}
