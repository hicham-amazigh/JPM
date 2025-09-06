package com.jpm.core.api;

import java.nio.file.Path;
import java.util.List;

public interface ProjectInspector {
    List<String> listModules(Path projectRoot) throws Exception;
}
