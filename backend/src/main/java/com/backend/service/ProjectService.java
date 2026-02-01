package com.backend.service;

import java.util.List;

import com.backend.dto.ProjectDTO;
import com.backend.exception.ClientException;
import com.backend.exception.ProjectException;

public interface ProjectService {
	ProjectDTO createProject(ProjectDTO project) throws ProjectException;

	List<ProjectDTO> getAllProjects() throws ProjectException;

	ProjectDTO getProjectById(Long id) throws ProjectException;

	ProjectDTO updateProject(Long id, ProjectDTO project) throws ProjectException, ClientException;

	void deleteProject(Long id) throws ProjectException;
}
