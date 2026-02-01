package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ProjectDTO;
import com.backend.exception.ClientException;
import com.backend.exception.ProjectException;
import com.backend.service.impl.ProjectServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectServiceImpl projectService;

	@PostMapping
	ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO project) throws ProjectException {
		ProjectDTO response = projectService.createProject(project);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	ResponseEntity<List<ProjectDTO>> getAllProjects() throws ProjectException {
		List<ProjectDTO> response = projectService.getAllProjects();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) throws ProjectException {
		ProjectDTO response = projectService.getProjectById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO project)
			throws ProjectException, ClientException {
		ProjectDTO response = projectService.updateProject(id, project);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteProject(@PathVariable Long id) throws ProjectException {
		projectService.deleteProject(id);
		return new ResponseEntity<>("Project Deleted Successfully", HttpStatus.OK);
	}
}
