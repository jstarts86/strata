package com.jstarts.strata.core.entities;

public record DecoratorEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        String expression,
        String targetId) implements CodeEntity {
    public DecoratorEntity {
        if (type == null)
            type = CodeEntityType.DECORATOR;
    }
}
