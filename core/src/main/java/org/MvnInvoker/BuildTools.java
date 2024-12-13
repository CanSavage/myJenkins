package org.MvnInvoker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * BuildTools: implements Build about CI
 * example ： maven , gradle , nodejs
 */
public class BuildTools {
    private static final Logger logger = LogManager.getLogger(BuildTools.class);

    public static String WorkingDir = "/RepositoryData/projects/";  //shell script Working dir
    public static String shDir = "/RepositoryData/projects/";       //shell script dir

    /**
     * Creates a shell script file with the provided content.
     *
     * @param scriptContent The content to be written into the shell script.
     * @return The created shell script file.
     */
    public static File getShFile(String scriptContent) {
        File scriptFile = new File(shDir, "temp_script_" + UUID.randomUUID() + ".sh");
        try {
            if (scriptFile.exists()) {
                scriptFile.delete();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(scriptFile))) {
                writer.write(scriptContent);
            }

            // Make the script executable
            scriptFile.setExecutable(true);
            logger.info("Shell script created at: " + scriptFile.getAbsolutePath());
        } catch (Exception e) {
            logger.error("Error creating shell script: " + e.getMessage(), e);
        }
        return scriptFile;
    }

    /**
     * Executes a shell script file.
     *
     * @param scriptFile The shell script file to be executed.
     */
    public static void executeShellScript(File scriptFile) {
        try {
            logger.info("Executing shell script: " + scriptFile.getAbsolutePath());

            ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", scriptFile.getAbsolutePath());
            processBuilder.directory(new File(WorkingDir));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Capture and log output of the script execution
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String output = reader.lines().collect(Collectors.joining("\n"));
                logger.info("Shell script output:\n" + output);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Shell script executed successfully.");
            } else {
                logger.error("Shell script execution failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            logger.error("Error executing shell script: " + e.getMessage(), e);
        } finally {
            // Clean up the temporary script file
            if (scriptFile != null && scriptFile.exists()) {
                boolean deleted = scriptFile.delete();
                if (deleted) {
                    logger.info("Temporary shell script file deleted successfully.");
                } else {
                    logger.warn("Failed to delete temporary shell script file.");
                }
            }
        }
    }


}
