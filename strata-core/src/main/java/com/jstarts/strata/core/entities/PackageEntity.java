package com.jstarts.strata.core.entities;

public record PackageEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId) implements CodeEntity {
    public PackageEntity {
        if (type == null)
            type = CodeEntityType.PACKAGE;
    }
}
