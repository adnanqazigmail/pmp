package com.aq.ppmtool.service;

import com.aq.ppmtool.domain.Project;
import com.aq.ppmtool.exception.ProjectIdException;
import com.aq.ppmtool.repos.ProjectRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepos projectRepos;

    public Project saveOrUpdateProject(Project project) {
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepos.save(project);
        }
        catch (Exception ex){
            throw new ProjectIdException("Project ID: " + project.getProjectIdentifier() + " alrady exists");
        }
    }

    public Project findProjectByIdentifier(String projectID){
        Project project = projectRepos.findByProjectIdentifier(projectID.toUpperCase());
        if(project == null) {
            throw new ProjectIdException("Project ID: " + projectID + " Does not exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepos.findAll();
    }

    public void deleteProjectByIdentifier(String projectID){
        Project project = projectRepos.findByProjectIdentifier(projectID.toUpperCase());
        if(project == null) {
            throw new ProjectIdException("Cannot Delete. Project ID: " + projectID + " Does not exist");
        }

        projectRepos.delete(project);
    }
}
