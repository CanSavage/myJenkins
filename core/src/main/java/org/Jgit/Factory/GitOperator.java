package org.Jgit.Factory;

public interface GitOperator {

    void gitClone(String command);

    void gitPull(String command);

    void gitTag(String command);

    void gitMerge(String command);


}
