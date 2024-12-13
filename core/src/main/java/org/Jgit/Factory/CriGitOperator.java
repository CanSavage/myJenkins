package org.Jgit.Factory;

import org.Jgit.RepositoryLockManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * CriGitOperator: implements GitOperator
 * example : git clone , git pull , git merge , create tag
 * pipeline dir: BaseDir + RepositoryDir
 */
public class CriGitOperator implements GitOperator {
    private static final Logger logger = LogManager.getLogger(CriGitOperator.class);
    private  String RepositoryDir;
    private String BaseDir;
    private GitCommand GC;

    public CriGitOperator(String RepositoryDir){
        //init pipelineDir
    }



    public void gitClone(String command) {
        Map<String,String> map = GC.getCommandByType("CrigitCLONE",command);
        try {
            logger.info("Executing git command: " + command);
            //String[] command = {"git", "clone", "--depth", "1", repoUrl, localPath};
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", command);
            processBuilder.directory(new File(BaseDir));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Capture and log output of the command execution
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String output = reader.lines().collect(Collectors.joining("\n"));
                logger.info("Git command output:\n" + output);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Git command executed successfully.");
            } else {
                logger.error("Git command execution failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            logger.error("Error executing git command: " + e.getMessage(), e);
        }
    }



    /**
     * Executes 'git pull' to update the local repository with remote changes.
     */
    public void gitPull(String command) {
        Map<String,String> map = GC.getCommandByType("CrigitPULL",command);
    }

    /**
     * Executes 'git merge' to merge a specified branch into the current branch.
     *
     * @param command The name of the branch to merge.
     */
    public void gitMerge(String command) {
        Map<String,String> map = GC.getCommandByType("CrigitMERGE",command);


    }

    /**
     * Executes 'git tag' to create a new tag in the repository.
     *
     * @param command The name of the tag to create.
     */
    public void gitTag(String command) {
        Map<String,String> map = GC.getCommandByType("CrigitTAG",command);
    }





}
