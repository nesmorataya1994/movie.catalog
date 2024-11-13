package com.movie.catalog.model.dto;

import lombok.Data;

@Data
public class MovieSearchRequest {
    private String searchTerm; // For searching in name or synopsis
    private Long categoryId;
    private Integer yearOfRelease;
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = "createdAt";
    private String sortDirection = "DESC";
}
