package org.Jgit.Factory;


import java.util.HashMap;
import java.util.Map;

public enum GitCommand {
    JgitPULL("JgitPULL") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    },
    JgitCLONE("JgitCLONE") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    },
    JgitTAG("JgitTAG") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    },
    JgitMERGE("JgitMERGE") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    },
    CrigitPULL("CrigitPULL") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    },
    CrigitCLONE("CrigitCLONE") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    },
    CrigitTAG("CrigitTAG") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    },
    CrigitMERGE("CrigitMERGE") {
        @Override
        public <T> T parseCommand(String command) {
            Map<String,String> map = new HashMap<>();
            map.put("","");
            return (T) map;
        }
    };

    public String CommandType;
    public abstract <T> T parseCommand(String command);

    GitCommand(String commandType) {
        this.CommandType = commandType;
    }



    public <T> T getCommandByType(String commandtype,String command) {
        for (GitCommand gitCommand : GitCommand.values()) {
            if (gitCommand.equals(commandtype)) {
                return parseCommand(command);
            }
        }
        throw new IllegalArgumentException("Invalid Git command: " + command);
    }
}

