package com.jstarts.strata.core.entities;

import java.util.Map;

public record FileEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        String moduleName) implements CodeEntity {
    public FileEntity {
        if (type == null)
            type = CodeEntityType.FILE;
    }

    @Override
    public Map<String, Object> toProperties() {
        Map<String, Object> props = CodeEntity.super.toProperties();
        props.put("module_name", moduleName);
        return props;
    }
}
