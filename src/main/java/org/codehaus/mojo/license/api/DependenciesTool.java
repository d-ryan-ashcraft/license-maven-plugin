package org.codehaus.mojo.license.api;

/*
 * #%L
 * License Maven Plugin
 * %%
 * Copyright (C) 2011 CodeLutin, Codehaus, Tony Chemit
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProject;

import java.util.List;
import java.util.SortedMap;

/**
 * A tool to deal with dependencies of a project.
 *
 * @author tchemit dev@tchemit.fr
 * @since 1.0
 */
public interface DependenciesTool
{

    /**
     * For a given {@code project}, obtain the universe of its dependencies after applying transitivity and
     * filtering rules given in the {@code configuration} object.
     *
     * Result is given in a map where keys are unique artifact id
     *
     * @param dependencies       the project dependencies
     * @param configuration      the configuration
     * @param localRepository    local repository used to resolv dependencies
     * @param remoteRepositories remote repositories used to resolv dependencies
     * @param cache              a optional cache where to keep resolved dependencies
     * @return the map of resolved dependencies indexed by their unique id.
     * @see MavenProjectDependenciesConfigurator
     */
    SortedMap<String, MavenProject> loadProjectDependencies( ResolvedProjectDependencies dependencies,
                                                             MavenProjectDependenciesConfigurator configuration,
                                                             ArtifactRepository localRepository,
                                                             List<ArtifactRepository> remoteRepositories,
                                                             SortedMap<String, MavenProject> cache );

    /**
     * Load project artifacts.
     *
     * @param localRepository    local repository used to resolv dependencies
     * @param remoteRepositories remote repositories used to resolv dependencies
     * @param project            the project to scan
     * @param reactorProjectDependencies reactor projects. Optional, only relevant if there is more than one)
     * @return the loaded project dependency artifacts
     * @throws DependenciesToolException if could not load project dependencies
     */
    ResolvedProjectDependencies loadProjectArtifacts( ArtifactRepository localRepository,
                                                      List<ArtifactRepository> remoteRepositories, MavenProject project,
                                                      List<MavenProject> reactorProjectDependencies )
        throws DependenciesToolException;
}
