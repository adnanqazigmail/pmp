package com.aq.ppmtool.service;

import com.aq.ppmtool.domain.Project;
import com.aq.ppmtool.repos.ProjectRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepos projectRepos;

    public Project saveOrUpdateProject(Project project) {
        //logic here
        return projectRepos.save(project);
    }
}
