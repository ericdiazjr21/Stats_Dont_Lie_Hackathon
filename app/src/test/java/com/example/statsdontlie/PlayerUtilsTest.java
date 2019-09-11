package com.example.statsdontlie;

import com.example.statsdontlie.utils.PlayerUtil;

import org.junit.Assert;
import org.junit.Test;

public class PlayerUtilsTest {


    @Test
    public void GivenEmptyStringWhenCreatePlayerPhotoCalledThenReturnEmptyString() {
        //Given
        String emptyStringFirstName = "";
        String emptyStringLastName = "";
        String expectedResult = "";

        //When
        String playerPhoto = PlayerUtil.createPlayerPhoto(emptyStringFirstName, emptyStringLastName);

        //Then
        Assert.assertEquals(expectedResult, playerPhoto);
    }
}
