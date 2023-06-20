package com.lordgasmic.bff.collection;

import com.lordgasmic.bff.collection.model.GasRequest;
import com.lordgasmic.bff.collection.model.WineFriendsRequest;
import com.lordgasmic.bff.collection.model.WineNoteRequest;
import com.lordgasmic.bff.collection.model.WineRatingEditRequest;
import com.lordgasmic.bff.collection.model.WineRatingRequest;
import com.lordgasmic.bff.collection.model.WineRequest;
import com.lordgasmic.bff.collection.model.WineryRequest;
import com.lordgasmic.bff.dom.model.WineNoteResponse;
import com.lordgasmic.bff.dom.model.WineRatingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class CollectionService {

    private final CollectionClient client;

    public CollectionService(final CollectionClient client) {
        this.client = client;
    }

    public List<Object> getWineries() {
        return client.getWineries();
    }

    public Object getWineryById(final String id) {
        return client.getWineryById(id);
    }

    public Object getWines(final String wineId, final String wineryId) {
        return client.getWines(wineId, wineryId);
    }

    public WineNoteResponse getWineNotes(final String user, final Integer wineId) {
        return client.getWineNotes(user, wineId);
    }

    public List<WineRatingResponse> getWineRating(final String user, final Integer wineId) {
        return client.getWineRating(user, wineId);
    }

    public Object addWine(final WineRequest wineRequest) {
        return client.addWine(wineRequest);
    }

    public Object addWinery(final WineryRequest wineryRequest) {
        return client.addWinery(wineryRequest);
    }

    public Object addWineNotes(final WineNoteRequest wineNoteRequest) {
        return client.addWineNotes(wineNoteRequest);
    }

    public Object addWineRating(final WineRatingRequest request) {
        return client.addWineRating(request);
    }

    public Object editWineRating(final WineRatingEditRequest request) {
        return client.editWineRating(request);
    }

    public Object getWineFriends(final WineFriendsRequest request) {
        return client.getWineRatingByUsersByWineId(request);
    }

    public Object addWineImage(final String contentType, final int wineId, final String label, final MultipartFile imageFile) {
        return client.addWineImage(Map.of("content-type", contentType), wineId, label, imageFile);
    }

    public Object getWineImages(final int wineId) {
        return client.getWineImages(wineId);
    }

    public Object addGas(final GasRequest request) {
        return client.addGas(request);
    }

    public Object getGas(final String vehicle) {
        return client.getGas(vehicle);
    }

    public Object getAllRecipes() {
        return client.getAllRecipes();
    }

    public Object getRecipeById(final int id) {
        return client.getRecipe(id);
    }

    public Object getIngredientsByRecipeId(final int recipeId) {
        return client.getIngredientsForRecipeId(recipeId);
    }

    public Object getTagByRecipeId(final int recipeId) {
        return client.getTagsForRecipeId(recipeId);
    }

    public Object getDirectionsForRecipeId(final int recipeId) {
        return client.getDirectionsForRecipeId(recipeId);
    }
}
