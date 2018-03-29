package com.misztal.tvshows

import javax.inject.Inject

/**
 * Created by kmisztal on 29/03/2018.
 *
 * @author Krzysztof Misztal
 */
class ExceptionHandler @Inject constructor() {

    /**
     * @return string res of error message
     */
    fun getErrorMessage(throwable: Throwable): Int = R.string.error_unknown

}