package com.shmuelrosansky.zmanim.shared;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class ZmanimCalculatorTest {

    @Test
    public void getZmanim_nullRequestThrows() {
        try {
            ZmanimCalculator.getZmanim(null);
            fail("ZmanimCalculator.getZmanim should not allow a null");
        } catch (IllegalArgumentException expected) {
            assertThat(expected).hasMessageThat().contains("ZmanRequest cannot be null!");
        }
    }
}