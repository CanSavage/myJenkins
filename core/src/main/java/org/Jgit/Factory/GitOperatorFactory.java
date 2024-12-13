package org.Jgit.Factory;

public interface GitOperatorFactory {
    GitOperator createJGit(String Dir);

    GitOperator createCriGit(String Dir);

}
