package com.jpm.core.spi;

import com.jpm.core.api.BuildTool;
import com.jpm.core.api.ProjectInspector;
import com.jpm.core.impl.MavenProjectInspector;

public final class InspectorFactory {
    private InspectorFactory() {}
    public static ProjectInspector forTool(BuildTool tool) {
        return switch (tool) {
            case MAVEN -> new MavenProjectInspector();
            case GRADLE -> throw new UnsupportedOperationException("gradle not yet supported");
        };
    }
}
