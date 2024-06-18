package org.example;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class CharacterImplementation {
    private RequestSpecification request;
    private Response response;

    @Before
    public void before(){
        RestAssured.baseURI = "https://rickandmortyapi.com/api/";
    }

    @Given("Rick and Morty API is available")
    public void RickAndMortyAPIIsAvailable() {
        request = given().contentType(ContentType.JSON).basePath("/character");
    }
    @When("I send a GET request to retrieve the character by name {string}")
    public void iSendAGETRequestToRetrieveTheCharacterWithName(String name) {
        response = request.given().param("name",name).get();
        response.then().log().body();
    }
    @Then("the response status code is {int}")
    public void theAPIResponseStatusCodeIs(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response is a list of characters which names equals {string}")
    public void theResponseIsAListOfCharactersWichNamesContainsRi(String name) {
        JsonPath characters = new JsonPath(response.body().asString());
        List<String> names = characters.get("results.name");
        for (String s : names) {
            assertTrue("The value of the name is not expected", s.contains(name));
        }

    }
    @Then("the response is a list of characters suggestions")
    public void theResponseIsAListOfCharactersSuggestions() {
        response.then().body("results.name", notNullValue());
    }

    @And("validate that the response body contains the message {string}")
    public void validateThatTheResponseBodyContainsTheObject(String error) {
        response.then().body("error",equalTo(error));
    }

    @Then("validate the response body status field is not null")
    public void validateTheResponseBodyStatusFieldIsNotNull() {
        response.then().body("results.status", notNullValue());
    }
    @And("validate the response body created field is not null")
    public void validateTheResponseBodyLocationIsNotNull() {
        response.then().body("results.created", notNullValue());
    }

    @When("I send a GET request to retrieve the character by name {string} and status {string}")
    public void iSendAGETRequestToRetrieveTheCharacterByNameAndStatus(String name, String status) {
        response = request.given().param("name",name).param("status",status).get();
        response.then().log().body();
    }
    @And("every character status equals {string}")
    public void everyCharacterStatusEquals(String status) {
        JsonPath characters = new JsonPath(response.body().asString());
        List<String> list = characters.get("results.status");
        for (String s : list) {
            assertTrue("The value of the status is not expected", s.contains(status));
        }
    }

    @When("I send a GET request to retrieve the character with id {string}")
    public void iSendAGETRequestToRetrieveTheCharacterWithId(String id) {
        response = request.given().get(id);
        response.then().log().body();
    }
    @And("id of the character equals {int}")
    public void idOfTheCharacterEquals(int id) {
        response.then().body("id", equalTo(id));
    }

    @When("I send a GET request to retrieve the characters with ids {string} {string} {string}")
    public void iSendAGETRequestToRetrieveTheCharactersWithId(String id1, String id2, String id3) {
        response = request.given().get( id1 + "," + id2 + "," + id3);
        response.then().log().body();
    }

    @Then("the API response contains three characters that match with IDs {string} {string} {string}")
    public void theAPIResponseContainsCharactersThatMatchWithIDs(String id1, String id2, String id3) {
        JsonPath characters = new JsonPath(response.body().asString());
        List<String> list = characters.get("results.id");
        for (String s : list) {
            assertTrue("The value of the id1 is not expected", s.contains(id1));
            assertTrue("The value of the id1 is not expected", s.contains(id2));
            assertTrue("The value of the id1 is not expected", s.contains(id3));
        }
    }

}
