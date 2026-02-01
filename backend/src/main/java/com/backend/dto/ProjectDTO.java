package com.backend.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProjectDTO {

	private Long id;
	@NotBlank(message = "Title should not be empty!")
	private String title;
	@Size(max = 500, message = "Description must be at most 500 characters")
	private String description;
	@Pattern(regexp = "^(https?://).+$", message = "Thumbnail URL must be a valid URL")
	private String thumbnailUrl;
	@Pattern(regexp = "^(https?://).+$", message = "Live Demo URL must be a valid URL")
	private String liveDemoUrl;
	@Pattern(regexp = "^(https?://).+$", message = "Source Demo URL must be a valid URL")
	private String sourceDemoUrl;
	@NotNull
	private Long clientId;
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getLiveDemoUrl() {
		return liveDemoUrl;
	}

	public void setLiveDemoUrl(String liveDemoUrl) {
		this.liveDemoUrl = liveDemoUrl;
	}

	public String getSourceDemoUrl() {
		return sourceDemoUrl;
	}

	public void setSourceDemoUrl(String sourceDemoUrl) {
		this.sourceDemoUrl = sourceDemoUrl;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
