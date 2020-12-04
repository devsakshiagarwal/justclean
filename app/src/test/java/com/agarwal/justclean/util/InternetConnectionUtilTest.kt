package com.agarwal.justclean.util

import com.agarwal.justclean.utils.InternetConnectionUtil
import org.junit.Assert
import org.junit.Test

class InternetConnectionUtilTest {

  private val internetConnectionUtil = InternetConnectionUtil

  @Test fun test_if_sum_is_valid() {
    Assert.assertEquals(internetConnectionUtil.giveSum(5,6), 11)
    Assert.assertNotEquals(internetConnectionUtil.giveSum(5,6), 0)
    Assert.assertNotEquals(internetConnectionUtil.giveSum(5,6), 4)
  }
}