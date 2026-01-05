package com.jstarts.strata.core.entities;

import java.util.List;

public record ClassEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        List<String> superClasses) implements CodeEntity {
    public ClassEntity {
        if (type == null)
            type = CodeEntityType.CLASS;
    }
}
