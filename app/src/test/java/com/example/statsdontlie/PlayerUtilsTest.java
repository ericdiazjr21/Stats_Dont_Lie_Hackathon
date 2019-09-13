package com.example.statsdontlie;

import com.example.statsdontlie.utils.PlayerUtil;

import org.junit.Assert;
import org.junit.Test;

public class PlayerUtilsTest {

    @Test
    public void GivenEmptyStringWhenCreatePlayerPhotoCalledThenReturnDefaultPhotoUrl() {
        //Given
        String emptyStringFirstName = "";
        String emptyStringLastName = "";
        String expectedDefaultPhotoUrl = "https://kytopen.com/wp-content/uploads/2018/01/placeholder_avatar-400x400.png";

        //When
        String playerPhotoUrl = PlayerUtil.getPlayerPhotoUrl(emptyStringFirstName, emptyStringLastName);

        //Then
        Assert.assertEquals(expectedDefaultPhotoUrl, playerPhotoUrl);
    }

    @Test
    public void GivenNullStringWhenCreatePlayerPhotoCalledThenReturnDefaultPhotoUrl() {
        //Given
        String emptyStringFirstName = null;
        String emptyStringLastName = null;
        String expectedDefaultPhotoUrl = "https://kytopen.com/wp-content/uploads/2018/01/placeholder_avatar-400x400.png";

        //When
        String playerPhotoUrl = PlayerUtil.getPlayerPhotoUrl(emptyStringFirstName, emptyStringLastName);

        //Then
        Assert.assertEquals(expectedDefaultPhotoUrl, playerPhotoUrl);
    }
}
