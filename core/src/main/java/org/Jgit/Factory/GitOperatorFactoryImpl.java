package org.Jgit.Factory;

public class GitOperatorFactoryImpl implements GitOperatorFactory{
    @Override
    public JGitOperator createJGit(String Dir) {
        return new JGitOperator(Dir);
    }

    @Override
    public CriGitOperator createCriGit(String Dir) {
        return new CriGitOperator(Dir);
    }


}
