
package com.example.think.upcomingmoviesdemo.data.upcoming;

import com.example.think.upcomingmoviesdemo.data.model.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Movies {

    @JsonProperty("results")
    private List<Movie> results = null;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("total_results")
    private Integer totalResults;
    @JsonProperty("dates")
    private Dates dates;
    @JsonProperty("total_pages")
    private Integer totalPages;

    public Movies(){}

    public Movies(List<Movie> results, Integer page, Integer totalResults, Dates dates, Integer totalPages) {
        this.results = results;
        this.page = page;
        this.totalResults = totalResults;
        this.dates = dates;
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
