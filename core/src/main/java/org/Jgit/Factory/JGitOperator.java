package org.Jgit.Factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.Jgit.RepositoryLockManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * JGitOperator : implements GitOperator
 * example : git clone , git pull , git merge , create tag
 * pipeline dir: BaseDir + RepositoryDir
 */
public class JGitOperator implements GitOperator {
    private static final Logger logger = LogManager.getLogger(JGitOperator.class);
    private  String RepositoryDir;
    private String BaseDir;
    private GitCommand GC;

    public JGitOperator (String RepositoryDir){
        //init pipelineDir
    }


    @Override
    public void gitClone(String command) {
        Map<String,String> map = GC.getCommandByType("JgitCLONE",command);
        // 浅克隆的深度（仅克隆最新的 1 次提交）
        int depth = 1;
        try {
            // 配置 CloneCommand
            CloneCommand cloneCommand = getGit().cloneRepository()
                    .setURI(map.get("uri"))
                    .setDirectory(new File(RepositoryDir))
                    .setDepth(depth)  // 浅克隆，深度为 1
                    .setBranch(map.get("branch"))
                    .setCloneAllBranches(false); // 仅克隆默认分支

            cloneCommand.call();
            System.out.println("浅克隆完成，克隆路径为: " + RepositoryDir);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("克隆仓库时出现错误: " + e.getMessage());
        }

    }


    /**
     * Executes 'git pull' to update the local repository with remote changes.
     */
    @Override
    public void gitPull(String command) {
        Map<String,String> map =  GC.getCommandByType("JgitPULL",command);
        try {
            logger.info("Executing git pull...");
            getGit().pull().call();
        } catch (Exception e) {
            logger.error("Error executing git pull: " + e.getMessage());
        }
    }

    /**
     * Executes 'git merge' to merge a specified branch into the current branch.
     *
     * @param command The name of the branch to merge.
     */
    @Override
    public void gitMerge(String command) {
        Map<String,String> map = GC.getCommandByType("JgitMERGE",command);
        try {
            logger.info("Executing git merge " + map.get("branchName") + "...");
            try (Git git = getGit()) {
                git.merge().include(git.getRepository().findRef(map.get("branchName"))).call();
                logger.info("Git merge completed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error executing git merge: " + e.getMessage());
        }
    }

    /**
     * Executes 'git tag' to create a new tag in the repository.
     *
     * @param command The name of the tag to create.
     */
    @Override
    public void gitTag(String command) {
        Map<String,String> map =  GC.getCommandByType("JgitTAG",command);
        try {
            logger.info("Creating tag: " + map.get("tagName") + "...");
            try (Git git = getGit()) {
                git.tag().setName(map.get("tagName")).call();
                logger.info("Tag " + map.get("tagName") + " created successfully.");
            }
        } catch (Exception e) {
            logger.error("Error creating tag: " + e.getMessage());
        }
    }

    public void gitDiff() {

    }

    public Git getGit() {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try {
            Repository repository = builder.setGitDir(new File(RepositoryDir))
                    .readEnvironment() // scan environment GIT_* variables
                    .findGitDir() // scan up the file system tree
                    .build();

            return new Git(repository);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
