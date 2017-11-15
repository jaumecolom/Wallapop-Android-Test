package com.jcolom.wallapoptest.domain.model;

import java.util.List;

/**
 * Created by Jaume on 15/11/17.
 */

public class ComicsResponse {

    private ApiData data;

    public ComicsResponse(ApiData data) {
        this.data = data;
    }

    public ApiData getData() {
        return data;
    }

    public void setData(ApiData data) {
        this.data = data;
    }

    public class ApiData{
        private List<Comic> results;

        private ApiData(List<Comic> results) {
            this.results = results;
        }

        public List<Comic> getResults() {
            return results;
        }

        public void setResults(List<Comic> results) {
            this.results = results;
        }
    }
}
