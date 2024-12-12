package org.example;

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
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * JgitTools: implements git about CI
 * example ： git pull , git merge , create tag ,git diff
 */
public class JgitTools{
    private static final Logger logger = LogManager.getLogger(JgitTools.class);
    public static String RepositoryDir = "/RepositoryData/projects/";

    public static void getClone(String repoUrl) {
        // 浅克隆的深度（仅克隆最新的 1 次提交）
        int depth = 1;
        try {
            // 配置 CloneCommand
            CloneCommand cloneCommand = Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(new File(RepositoryDir))
                    .setDepth(depth)  // 浅克隆，深度为 1
                    .setCloneAllBranches(false); // 仅克隆默认分支

            // 克隆仓库
            Git git = cloneCommand.call();
            System.out.println("浅克隆完成，克隆路径为: " + RepositoryDir);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("克隆仓库时出现错误: " + e.getMessage());
        }

    }

    public static Repository getRepository() {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try {
            Repository repository = builder.setGitDir(new File(RepositoryDir))
                    .readEnvironment() // scan environment GIT_* variables
                    .findGitDir() // scan up the file system tree
                    .build();

            return repository;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Git getGit() {
        Git git = new Git(getRepository());
        return git;
    }

    /**
     * Executes 'git pull' to update the local repository with remote changes.
     */
    public static void gitPull() {
        // 在操作 Git 之前，获取锁
        ReentrantLock lock = RepositoryLockManager.getLock("/path/to/repo");
        try {
            lock.lock();
            logger.info("Executing git pull...");
            try (Git git = getGit()) {
                git.pull().call();
                System.out.println("Git pull completed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error executing git pull: " + e.getMessage());
        }finally {
            lock.unlock();
        }
    }

    /**
     * Executes 'git merge' to merge a specified branch into the current branch.
     *
     * @param branchName The name of the branch to merge.
     */
    public void gitMerge(String branchName) {
        try {
            logger.info("Executing git merge " + branchName + "...");
            try (Git git = getGit()) {
                git.merge().include(git.getRepository().findRef(branchName)).call();
                logger.info("Git merge completed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error executing git merge: " + e.getMessage());
        }
    }

    /**
     * Executes 'git tag' to create a new tag in the repository.
     *
     * @param tagName The name of the tag to create.
     */
    public void createTag(String tagName) {
        try {
            logger.info("Creating tag: " + tagName + "...");
            try (Git git = getGit()) {
                git.tag().setName(tagName).call();
                logger.info("Tag " + tagName + " created successfully.");
            }
        } catch (Exception e) {
            logger.error("Error creating tag: " + e.getMessage());
        }
    }

    /**
     * Executes 'git diff' to show changes between the working directory and git repository.
     *
     * @param repoPath The path to the local git repository.
     */
    public void gitDiff(String repoPath) {
        try {
            logger.info("Executing git diff...");
            try (Git git = getGit()) {
                String diff = git.diff().call().toString();
                logger.info("Output:\n" + diff);
            }
        } catch (Exception e) {
            logger.error("Error executing git diff: " + e.getMessage());
        }
    }

    /**
     * Calls git commands using the given arguments.
     *
     * @param command The git command to be executed (e.g., "git pull", "git merge").
     * @param workingDir The directory where the git command will be executed.
     */
    public static void callGitCommand(String command, String workingDir) {
        try {
            logger.info("Executing git command: " + command);
            //String[] command = {"git", "clone", "--depth", "1", repoUrl, localPath};
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", command);
            processBuilder.directory(new File(workingDir));
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


}
