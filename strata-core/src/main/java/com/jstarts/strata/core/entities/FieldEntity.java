package com.jstarts.strata.core.entities;

public record FieldEntity(
        String id,
        String name,
        CodeEntityType type,
        SourceLocation location,
        String parentId,
        String declaredType,
        boolean isTyped,
        boolean isAssigned,
        boolean isClassVariable) implements CodeEntity {
    public FieldEntity {
        if (type == null)
            type = CodeEntityType.FIELD;
    }
}
