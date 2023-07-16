package com.siifii.thamanyah.presentation.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.siifii.thamanyah.R
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapter {

    @BindingAdapter("loadImageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        view.load(url) {
            crossfade(true)
//            placeholder(R.drawable.placeholder_loader)
            transformations(RoundedCornersTransformation(16f))
            scale(Scale.FIT)
        }
    }

    @BindingAdapter("episodes", "duration")
    @JvmStatic
    fun formatEpisodeAndDuration(view: TextView, episodeCount: Int, durationInMilliseconds: Int) {
        val hours = durationInMilliseconds / (1000 * 60 * 60)
        val formattedString =
            view.context.getString(R.string.episode_and_duration_format, episodeCount, hours)
        view.text = formattedString
    }

    @BindingAdapter("setDataFormat")
    @JvmStatic
    fun dateFormatter(view: TextView, dateInString: String) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE dd MMMM yyyy", Locale("ar"))

        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(dateInString)
        val formattedDate = date?.let { outputFormat.format(it) }

        view.text = formattedDate
    }

}

