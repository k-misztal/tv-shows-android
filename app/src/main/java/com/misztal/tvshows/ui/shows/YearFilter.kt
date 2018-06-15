package com.misztal.tvshows.ui.shows

/**
 * Created by kmisztal on 15/06/2018.
 *
 * @author Krzysztof Misztal
 */
data class YearFilter(
        val from: Int,
        val to: Int
) {
    fun isValid() = from <= to
}