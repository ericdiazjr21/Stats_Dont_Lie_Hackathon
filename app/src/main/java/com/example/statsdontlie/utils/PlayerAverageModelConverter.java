package com.example.statsdontlie.utils;

import com.example.statsdontlie.model.PlayerAverageModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PlayerAverageModelConverter {

    private PlayerAverageModelConverter() {
    }

    public static String playerAverageSerializer(List<PlayerAverageModel> playerAverageModels) {
        return new Gson().toJson(playerAverageModels);
    }

    public static List<PlayerAverageModel> playerAverageDeserializer(String json) {
        if (json == null || json.equals("")) return Collections.emptyList();
        Type listType = new TypeToken<ArrayList<PlayerAverageModel>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }
}
