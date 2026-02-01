package com.backend.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.ClientDTO;
import com.backend.dto.ProjectDTO;
import com.backend.entity.Client;
import com.backend.entity.Project;
import com.backend.exception.ClientException;
import com.backend.exception.ProjectException;
import com.backend.repository.ProjectRepository;
import com.backend.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ClientServiceImpl clientService;

	@Autowired
	ProjectRepository projectRepo;

	@Override
	public ProjectDTO createProject(ProjectDTO project) throws ProjectException {
		Long id = project.getId();
		if (id != null) {
			Optional<Project> optionalProject = projectRepo.findById(id);
			Project value = optionalProject.get();
			if (value != null)
				throw new ProjectException("ProjectService.PROJECT_ALREADY_EXISTS");
		}
		project.setCreatedAt(LocalDateTime.now());
		Project newProject = modelMapper.map(project, Project.class);
		Project response = projectRepo.save(newProject);
		return modelMapper.map(response, ProjectDTO.class);
	}

	@Override
	public List<ProjectDTO> getAllProjects() throws ProjectException {
		List<Project> projects = projectRepo.findAll();
		if (projects == null || projects.isEmpty()) {
			throw new ProjectException("ProjectService.NO_PROJECTS");
		}
		List<ProjectDTO> response = new ArrayList<>();
		for (Project p : projects) {
			response.add(modelMapper.map(p, ProjectDTO.class));
		}
		return response;
	}

	@Override
	public ProjectDTO getProjectById(Long id) throws ProjectException {
		Optional<Project> optional = projectRepo.findById(id);
		Project optionalProject = optional.get();
		if (optionalProject == null) {
			throw new ProjectException("ProjectService.PROJECT_NOT_FOUND");
		}
		ProjectDTO project = modelMapper.map(optionalProject, ProjectDTO.class);
		return project;
	}

	@Override
	public ProjectDTO updateProject(Long id, ProjectDTO project) throws ProjectException, ClientException {
		Optional<Project> optional = projectRepo.findById(id);
		Project optionalProject = optional.get();
		if (optionalProject == null) {
			throw new ProjectException("ProjectService.PROJECT_NOT_FOUND");
		}
		if (project.getId() != null) {
			optionalProject.setId(project.getId());
		}
		if (project.getTitle() != null) {
			optionalProject.setTitle(project.getTitle());
		}
		if (project.getDescription() != null) {
			optionalProject.setDescription(project.getDescription());
		}
		if (project.getThumbnailUrl() != null) {
			optionalProject.setThumbnailUrl(project.getThumbnailUrl());
		}
		if (project.getLiveDemoUrl() != null) {
			optionalProject.setLiveDemoUrl(project.getLiveDemoUrl());
		}
		if (project.getSourceDemoUrl() != null) {
			optionalProject.setSourceDemoUrl(project.getSourceDemoUrl());
		}
		if (project.getClientId() != null) {
			ClientDTO fetchedClient = clientService.getClientById(id);
			Client client = modelMapper.map(fetchedClient, Client.class);
			optionalProject.setClient(client);
		}

		Project savedProject = projectRepo.save(optionalProject);
		ProjectDTO response = modelMapper.map(savedProject, ProjectDTO.class);
		return response;
	}

	@Override
	public void deleteProject(Long id) throws ProjectException {
		Optional<Project> optional = projectRepo.findById(id);
		Project optionalProject = optional.get();
		if (optionalProject == null) {
			throw new ProjectException("ProjectService.PROJECT_NOT_FOUND");
		}
		projectRepo.deleteById(id);

	}

}
