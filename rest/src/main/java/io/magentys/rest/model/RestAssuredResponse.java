package io.magentys.rest.model;

import io.restassured.response.Response;

import java.util.function.Supplier;

public class RestAssuredResponse<T> implements RestResponse<T> {

    private final Response response;
    private final T data;

    public RestAssuredResponse(final Response response, final T data) {
        this.response = response;
        this.data = data;
    }

    public RestAssuredResponse(final Response response) {
        this(response, null);
    }

    @Override
    public T data() {
        return data;
    }

    @Override
    public int statusCode() {
        return response.statusCode();
    }

    @Override
    public Body body() {
        return RestAssuredBody.from(response.body());
    }

    @Override
    public boolean failed() {
        return !ok();
    }

    @Override
    public boolean ok() {
        return statusCode() < 300;
    }

    public static <T> RestResponse<T> constructFrom(Response response, Supplier<T> data){
        if (response.statusCode() < 300) {
            return new RestAssuredResponse<T>(response);
        } else {
            return new RestAssuredResponse<T>(response, data.get());
        }
    }
}