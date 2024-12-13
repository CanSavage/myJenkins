package org;

import org.Jgit.Factory.*;

public class PipeLine {

    public void test(){
        // 创建工厂实例
        GitOperatorFactory gitOperatorFactory = new GitOperatorFactoryImpl();
        // 使用工厂创建Git操作对象，注入仓库目录
        GitOperator jGitOperator =gitOperatorFactory.createJGit("");
        GitOperator criGitOperator = gitOperatorFactory.createCriGit("");
        // 执行操作
        jGitOperator.gitClone("git clone asdadas.git");
    }


}
