package com.portosdeveloper.protecworkshopapp.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

@Composable
fun QRCodeGenerator(
    modifier: Modifier,
    text: String,
    size: Int
) {

    val barcodeEncoder : BarcodeEncoder = BarcodeEncoder()
    val bitmap : Bitmap = barcodeEncoder.encodeBitmap(
        text,
        BarcodeFormat.QR_CODE,
        size,
        size
    )

    Image(
        modifier = modifier,
        bitmap = bitmap.asImageBitmap(),
        contentDescription = " QR Code"
    )
}
